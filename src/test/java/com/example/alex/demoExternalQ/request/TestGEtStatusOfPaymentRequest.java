package com.example.alex.demoExternalQ.request;

import com.example.alex.demoExternalQ.BaseTestConfig;
import com.example.alex.demoExternalQ.model.status.request.DestinationForStatus;
import com.example.alex.demoExternalQ.model.status.request.GetStatusOfPaymentRequest;
import com.example.alex.demoExternalQ.model.status.request.PaymentForStatus;
import com.example.alex.demoExternalQ.model.transfer.request.Extra;
import com.example.alex.demoExternalQ.utils.JAXBUtils;
import org.junit.Test;

import javax.xml.bind.JAXBException;
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

    @Test
    public void testDeserialize() throws JAXBException {
        String json = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "  <request>\n" +
                "    <request-type>pay</request-type>\n" +
                "    <extra name=\"password\">4234</extra>\n" +
                "    <terminal-id>123</terminal-id>\n" +
                "    <status>\n" +
                "      <payment>\n" +
                "        <transaction-number>12345678</transaction-number>\n" +
                "        <to>\n" +
                "             <account-number>79181234567</account-number>\n" +
                "        </to>\n" +
                "      </payment>\n" +
                "    </status>\n" +
                "  </request>";

        GetStatusOfPaymentRequest getStatusOfPaymentRequest = JAXBUtils.fromXML(json, GetStatusOfPaymentRequest.class);
        System.out.println(getStatusOfPaymentRequest);
    }
}
