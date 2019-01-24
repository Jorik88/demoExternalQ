package com.example.alex.demoExternalQ.request;

import com.example.alex.demoExternalQ.BaseTestConfig;
import com.example.alex.demoExternalQ.model.request.*;
import org.junit.Test;
import java.util.Currency;

public class TestTransactionRequest extends BaseTestConfig {

    private String filePath = "file1.xml";

    @Test
    public void testCreateRequest() {
        TransactionRequest transactionRequest = new TransactionRequest();

        Extra extra = new Extra();
        extra.setValue("some password");

        Source source = new Source(Currency.getInstance("RUB"));

        Destination destination = new Destination();
        destination.setAmount("232.33");
        destination.setCurrency("RUB");
        destination.setAccountNumber("some account");
        destination.setServiceId("some service id");

        PaymentRequest payment = new PaymentRequest("transaction number", source, destination);

        Auth auth = new Auth(payment);

        transactionRequest.setAuth(auth);
        transactionRequest.setExtra(extra);
        transactionRequest.setRequestType("pay");
        transactionRequest.setTerminalId("some terminal id");

        writeToFile(transactionRequest, TransactionRequest.class, filePath);
    }

}
