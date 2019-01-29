package com.example.alex.demoExternalQ.response;

import com.example.alex.demoExternalQ.BaseTestConfig;
import com.example.alex.demoExternalQ.model.transfer.request.Destination;
import com.example.alex.demoExternalQ.model.transfer.request.Source;
import com.example.alex.demoExternalQ.model.transfer.response.Balance;
import com.example.alex.demoExternalQ.model.transfer.response.ResultCode;
import com.example.alex.demoExternalQ.model.transfer.response.TransactionPaymentResponse;
import com.example.alex.demoExternalQ.model.transfer.response.TransactionResponse;
import org.junit.Test;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import java.util.Date;
import java.util.List;

public class TestTransactionResponse extends BaseTestConfig {

    private String filePath = "trResponse.xml";

    @Test
    public void testWriteToFile() {
        TransactionResponse response = new TransactionResponse();

        Source source = new Source(Currency.getInstance("RUB").getCurrencyCode(), new BigDecimal(42.42));
        Destination destination = new Destination(new BigDecimal(534.53), Currency.getInstance("RUB").getCurrencyCode(), 643L, "account number");

        TransactionPaymentResponse paymentResponse = new TransactionPaymentResponse(source, destination);
        paymentResponse.setFatalError(false);
        paymentResponse.setFinalStatus(true);
        paymentResponse.setStatus("status = 60");
        paymentResponse.setResultCode(0);
        paymentResponse.setTransactionDate(new Date());
        paymentResponse.setTransactionNumber(242L);
        paymentResponse.setTxnId(424L);

        List<Balance> balances = Arrays.asList(new Balance("code 234", new BigDecimal(232.32)), new Balance("code 643", BigDecimal.TEN));
        response.setBalances(balances);
        response.setPaymentResponse(paymentResponse);

        writeToFile(response, TransactionResponse.class, filePath);

    }

    @Test
    public void testDeserialize() throws JAXBException {
        String value = "<response>\n" +
                "    <payment status=\"status 60\" txn_id=\"6960\" transaction-number=\"342\" result-code=\"0\" final-status=\"true\" fatal-error=\"false\" txn-date=\"24.01.2019 15:11:37\">\n" +
                "        <from>\n" +
                "            <ccy>232.33</ccy>\n" +
                "            <amount>RUB</amount>\n" +
                "        </from>\n" +
                "        <to>\n" +
                "            <amount>some service id</amount>\n" +
                "            <ccy>2323.3</ccy>\n" +
                "            <service-id>643</service-id>\n" +
                "            <account-number>account number</account-number>\n" +
                "        </to>\n" +
                "    </payment>\n" +
                "    <balances>\n" +
                "        <balance code=\"code 234\">32.3</balance>\n" +
                "        <balance code=\"code 643\">34.2</balance>\n" +
                "    </balances>\n" +
                "</response>\n";

        JAXBContext jaxbContext = JAXBContext.newInstance(TransactionResponse.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        TransactionResponse response = (TransactionResponse) jaxbUnmarshaller.unmarshal(new StringReader(value));
        System.out.println(response);
    }
}
