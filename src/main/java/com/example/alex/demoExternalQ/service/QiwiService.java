package com.example.alex.demoExternalQ.service;

import com.example.alex.demoExternalQ.configuration.QiwiConfiguration;
import com.example.alex.demoExternalQ.enums.TransactionStatus;
import com.example.alex.demoExternalQ.model.base.TotalBalanceResponse;
import com.example.alex.demoExternalQ.model.base.TransferRequest;
import com.example.alex.demoExternalQ.model.status.request.GetStatusOfPaymentRequest;
import com.example.alex.demoExternalQ.model.status.request.DestinationForStatus;
import com.example.alex.demoExternalQ.model.status.request.PaymentForStatus;
import com.example.alex.demoExternalQ.model.status.response.GetStatusOfPaymentResponse;
import com.example.alex.demoExternalQ.model.transfer.request.*;
import com.example.alex.demoExternalQ.model.transfer.response.ResponseWithResultCode;
import com.example.alex.demoExternalQ.model.transfer.response.TransactionResponse;
import com.example.alex.demoExternalQ.utils.JAXBUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import static com.example.alex.demoExternalQ.enums.QiwiTransferType.CARD_SERVICE_ID;

@Slf4j
@Service
public class QiwiService implements IWalletService {

    private static final String TRANSFER_URL = "https://api.qiwi.com/xml/topup.jsp";
    private static final String REQUEST_TYPE = "pay";

    private QiwiConfiguration configuration;

    @Autowired
    public QiwiService(QiwiConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public TotalBalanceResponse getTotalBalance() {
        return null;
    }

    // TODO: 28.01.19 Нужно проверить account-number,обязательно ли нам иметь телефон пользователя.
    @Override
    public String transferExternal(TransferRequest transferRequest) {
        try {
            validateCardData(transferRequest.getTargetWallet());
            return transfer(transferRequest);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error was happened");
        }
    }

    private String transfer(TransferRequest transferRequest) throws IOException, JAXBException {
        TransactionRequest transactionRequest = initTransferRequest(transferRequest.getTransferRequestId(),
                transferRequest.getCurrency(), transferRequest.getTargetWallet(), transferRequest.getAmount());

        String response = sendRequest(transactionRequest);

        if (!response.contains("payment")) {
            ResponseWithResultCode responseWithResultCode = JAXBUtils
                    .fromXML(response, ResponseWithResultCode.class);
            log.warn("Error was happened, response={}", responseWithResultCode);
            throw new IllegalArgumentException("Some error happened " + responseWithResultCode);
        }

        TransactionResponse transactionResponse = JAXBUtils.fromXML(response, TransactionResponse.class);

        checkResponse(transactionRequest, transactionResponse);

        return String.valueOf(transactionResponse.getPaymentResponse().getTransactionNumber());
    }

    private void checkResponse(TransactionRequest transactionRequest, TransactionResponse response) {
        if (response == null || response.getPaymentResponse().getTxnId() == null
                || !transactionRequest.getAuth().getPayment().getTransactionNumber().equals(String.valueOf(response.getPaymentResponse().getTransactionNumber()))) {
            throw new IllegalArgumentException("Wrong transaction id");
        }
    }

    private TransactionRequest initTransferRequest(String transferId, String currency, String targetWalet, BigDecimal amount) {
        TransactionRequest transactionRequest = new TransactionRequest();

        Extra extra = new Extra();
        extra.setValue(configuration.getPassword());

        Source source = new Source(Currency.getInstance(currency));

        Destination destination = new Destination();
        destination.setAmount(amount.setScale(2, BigDecimal.ROUND_HALF_UP));
        destination.setCurrency(Currency.getInstance(currency).getCurrencyCode());
        destination.setAccountNumber(targetWalet);
        destination.setServiceId(CARD_SERVICE_ID);

        PaymentRequest payment = new PaymentRequest(transferId, source, destination);

        Auth auth = new Auth(payment);

        transactionRequest.setAuth(auth);
        transactionRequest.setExtra(extra);
        transactionRequest.setRequestType(REQUEST_TYPE);
        transactionRequest.setTerminalId(configuration.getTerminalId());
        return transactionRequest;
    }

    private void validateCardData(String targetWallet) {
        // TODO: 28.01.19 Нужно добавить проверку номера карточки на которую будет вывод.Если с этой карточки деньги не вводились => запретить вывод
    }

    @Override
    public TransactionStatus getTransactionStatus(String transactionId) {
        try {
            GetStatusOfPaymentRequest statusRequest = initStatusRequest(transactionId);
            String response = sendRequest(statusRequest);
            GetStatusOfPaymentResponse paymentResponse = JAXBUtils.fromXML(response, GetStatusOfPaymentResponse.class);

            if (paymentResponse.getPayment() != null && paymentResponse.getPayment().getStatus().equals("60") && paymentResponse.getPayment().isFinalStatus()) {
                return TransactionStatus.PROCESSED;
            } else if (paymentResponse.getPayment() == null ||
                    Integer.valueOf(paymentResponse.getPayment().getStatus()) >= 50 && Integer.valueOf(paymentResponse.getPayment().getStatus()) <= 59) {
                return TransactionStatus.WAITING;
            } else {
                return TransactionStatus.FAILED;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Some exception was happened");
        }
    }

    private GetStatusOfPaymentRequest initStatusRequest(String transactionId) {
        GetStatusOfPaymentRequest statusRequest = new GetStatusOfPaymentRequest();

        Extra extra = new Extra();
        extra.setValue(configuration.getPassword());

        DestinationForStatus destination = new DestinationForStatus(configuration.getAccountNumber());
        PaymentForStatus payment = new PaymentForStatus(Long.valueOf(transactionId), destination);

        statusRequest.setExtra(extra);
        statusRequest.setTerminalId(configuration.getTerminalId());
        statusRequest.setRequestType(REQUEST_TYPE);
        statusRequest.setPayment(Collections.singletonList(payment));
        return statusRequest;
    }

    @Override
    public String getPaymentSystemCode() {
        return null;
    }

    private String sendRequest(RequestWithExtraPassword request) throws IOException, JAXBException {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost httpPost = new HttpPost(TRANSFER_URL);
            httpPost.setEntity(new StringEntity(JAXBUtils.toXML(request)));

            try (CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
                return EntityUtils.toString(httpResponse.getEntity());
            }
        }
    }
}
