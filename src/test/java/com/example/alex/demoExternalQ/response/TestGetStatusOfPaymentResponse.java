package com.example.alex.demoExternalQ.response;

import com.example.alex.demoExternalQ.BaseTestConfig;
import com.example.alex.demoExternalQ.model.status.response.GetStatusOfPaymentResponse;
import com.example.alex.demoExternalQ.model.transfer.response.Balance;
import com.example.alex.demoExternalQ.model.transfer.response.BasePaymentResponse;
import com.example.alex.demoExternalQ.model.transfer.response.ResultCode;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

public class TestGetStatusOfPaymentResponse extends BaseTestConfig {

    private String filePath = "checkPaymentResponse.xml";

    @Test
    public void testWriteToFile() {
        GetStatusOfPaymentResponse statusResponse = new GetStatusOfPaymentResponse();

        BasePaymentResponse paymentResponse = new BasePaymentResponse();
        paymentResponse.setFatalError(false);
        paymentResponse.setFinalStatus(true);
        paymentResponse.setStatus("status = 60");
        paymentResponse.setResultCode(0);
        paymentResponse.setTransactionDate(new Date());
        paymentResponse.setTransactionNumber(242L);
        paymentResponse.setTxnId(424L);

        statusResponse.setPayment(paymentResponse);
        statusResponse.setBalance(Arrays.asList(new Balance("code 234", new BigDecimal(232.32)),
                new Balance("code 643", BigDecimal.TEN)));
        statusResponse.setResultCode(new ResultCode(false, 0));

        writeToFile(statusResponse, GetStatusOfPaymentResponse.class, filePath);
    }
}
