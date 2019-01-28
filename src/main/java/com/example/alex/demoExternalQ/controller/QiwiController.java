package com.example.alex.demoExternalQ.controller;

import com.example.alex.demoExternalQ.configuration.QiwiConfiguration;
import com.example.alex.demoExternalQ.model.base.TransferRequest;
import com.example.alex.demoExternalQ.model.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.Currency;
import static com.example.alex.demoExternalQ.enums.QiwiTransferType.CARD_SERVICE_ID;

@RestController
public class QiwiController {

    private static final String TRANSFER_OPERATION = "pay";

    @Autowired
    private QiwiConfiguration configuration;

    @GetMapping(value = "/get")
    public ResponseEntity get() {
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setCurrency("RUB");
        transferRequest.setAmount(new BigDecimal(423.43).setScale(2, BigDecimal.ROUND_HALF_UP));
        transferRequest.setTargetWallet("423543546546546");
        transferRequest.setTransferRequestId("4324");

        TransactionRequest transactionRequest = new TransactionRequest();

        Extra extra = new Extra();
        extra.setValue(configuration.getPassword());

        Source source = new Source(Currency.getInstance(transferRequest.getCurrency()));

        Destination destination = new Destination();
        destination.setAmount(transferRequest.getAmount());
        destination.setCurrency(Currency.getInstance(transferRequest.getCurrency()).getCurrencyCode());
        destination.setAccountNumber(transferRequest.getTargetWallet());
        destination.setServiceId(CARD_SERVICE_ID);

        PaymentRequest payment = new PaymentRequest(transferRequest.getTransferRequestId(), source, destination);

        Auth auth = new Auth(payment);

        transactionRequest.setAuth(auth);
        transactionRequest.setExtra(extra);
        transactionRequest.setRequestType(TRANSFER_OPERATION);
        transactionRequest.setTerminalId(configuration.getTerminalId());
        return ResponseEntity.ok(transactionRequest);
    }
}
