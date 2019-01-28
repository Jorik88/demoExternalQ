package com.example.alex.demoExternalQ.service;

import com.example.alex.demoExternalQ.configuration.QiwiConfiguration;
import com.example.alex.demoExternalQ.enums.TransactionStatus;
import com.example.alex.demoExternalQ.model.base.TotalBalanceResponse;
import com.example.alex.demoExternalQ.model.base.TransferRequest;
import com.example.alex.demoExternalQ.model.request.*;
import com.example.alex.demoExternalQ.model.response.TransactionResponse;
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
import java.util.Currency;

import static com.example.alex.demoExternalQ.enums.QiwiTransferType.CARD_SERVICE_ID;

@Slf4j
@Service
public class QiwiService implements IWalletService {

    private static final String TRANSFER_URL = "https://api.qiwi.com/xml/topup.jsp";
    private static final String TRANSFER_OPERATION = "pay";

    private QiwiConfiguration configuration;

    @Autowired
    public QiwiService(QiwiConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public TotalBalanceResponse getTotalBalance() {
        return null;
    }

    @Override
    public String transferExternal(TransferRequest transferRequest) {
        validateCardData(transferRequest.getTargetWallet());
        return null;
    }

    private void transfer(TransferRequest transferRequest) throws IOException, JAXBException {
        TransactionRequest transactionRequest = initTransferRequest(transferRequest.getTransferRequestId(),
                transferRequest.getCurrency(), transferRequest.getTargetWallet(), transferRequest.getAmount());

        TransactionResponse response = sendRequest(transactionRequest, TransactionResponse.class);

        checkResponse(transactionRequest, response);

    }

    private void checkResponse(TransactionRequest transactionRequest, TransactionResponse response) {
        if (response == null || response.getPaymentResponse().getTxnId() == null) {
            throw new IllegalArgumentException("Wrong transaction id");
        }
        if (!transactionRequest.getAuth().getPayment().getTransactionNumber().equals(response.getPaymentResponse().getTransactionNumber())) {
            throw new IllegalArgumentException("Wrong transaction id");
        }

    }

    public TransactionRequest initTransferRequest(String transferId, String currency, String targetWalet, BigDecimal amount) {
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
        transactionRequest.setRequestType(TRANSFER_OPERATION);
        transactionRequest.setTerminalId(configuration.getTerminalId());
        return transactionRequest;
    }

    private void validateCardData(String targetWallet) {
        // TODO: 28.01.19 Нужно добавить проверку номера карточки на которую будет вывод.Если с этой карточки деньги не вводились => запретить вывод
    }

    @Override
    public TransactionStatus getTransactionStatus(String transactionId) {
        return null;
    }

    @Override
    public String getPaymentSystemCode() {
        return null;
    }

    private <T> T sendRequest(RequestWithExtraPassword request, Class<T> clazz) throws IOException, JAXBException {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost httpPost = new HttpPost(TRANSFER_URL);
            httpPost.setEntity(new StringEntity(JAXBUtils.toXML(request)));

            try (CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
                return JAXBUtils.fromXML(EntityUtils.toString(httpResponse.getEntity()), clazz);
            }
        }
    }
}
