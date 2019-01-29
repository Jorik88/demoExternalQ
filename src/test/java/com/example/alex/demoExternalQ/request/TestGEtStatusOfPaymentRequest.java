package com.example.alex.demoExternalQ.request;

import com.example.alex.demoExternalQ.BaseTestConfig;
import com.example.alex.demoExternalQ.model.status.request.DestinationForStatus;
import com.example.alex.demoExternalQ.model.status.request.GetStatusOfPaymentRequest;
import com.example.alex.demoExternalQ.model.status.request.PaymentForStatus;
import com.example.alex.demoExternalQ.model.transfer.request.Extra;
import org.junit.Test;

import java.util.Collections;

public class TestGEtStatusOfPaymentRequest extends BaseTestConfig {

    private String filePath = "checkRequest.xml";

    @Test
    public void testWriteToFileRequest() {

        GetStatusOfPaymentRequest getStatusOfPaymentRequest = new GetStatusOfPaymentRequest();

        Extra extra = new Extra();
        extra.setValue("some password");

        DestinationForStatus destination = new DestinationForStatus(432423L);

        PaymentForStatus payment = new PaymentForStatus(4234L, destination);

        getStatusOfPaymentRequest.setPayment(Collections.singletonList(payment));
        getStatusOfPaymentRequest.setRequestType("pay");
        getStatusOfPaymentRequest.setTerminalId(3432L);
        getStatusOfPaymentRequest.setExtra(extra);

        writeToFile(getStatusOfPaymentRequest, GetStatusOfPaymentRequest.class, filePath);

    }
}
