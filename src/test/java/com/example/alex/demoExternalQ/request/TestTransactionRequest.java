package com.example.alex.demoExternalQ.request;

import com.example.alex.demoExternalQ.BaseTestConfig;
import com.example.alex.demoExternalQ.model.transfer.request.*;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.Currency;

public class TestTransactionRequest extends BaseTestConfig {

    private String filePath = "trRequest.xml";

    @Test
    public void testCreateRequest() {
        TransactionRequest transactionRequest = new TransactionRequest();

        Extra extra = new Extra();
        extra.setValue("some password");

        Source source = new Source(Currency.getInstance("RUB"));

        Destination destination = new Destination();
        destination.setAmount(new BigDecimal("232.33"));
        destination.setCurrency("RUB");
        destination.setAccountNumber("some account");
        destination.setServiceId(523523L);

        PaymentRequest payment = new PaymentRequest("transaction number", source, destination);

        Auth auth = new Auth(payment);

        transactionRequest.setAuth(auth);
        transactionRequest.setExtra(extra);
        transactionRequest.setRequestType("pay");
        transactionRequest.setTerminalId(23L);

        writeToFile(transactionRequest, TransactionRequest.class, filePath);
    }


    @Test
    public void testTrimAmount() {
        BigDecimal bigDecimal = new BigDecimal(423.4234).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal);
    }

    @Test
    public void testGenerateId() {
        System.out.println(4324224 + "_" + System.currentTimeMillis());
    }

}
