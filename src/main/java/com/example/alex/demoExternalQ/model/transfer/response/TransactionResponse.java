package com.example.alex.demoExternalQ.model.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionResponse {

    @XmlElement(name = "payment")
    private TransactionPaymentResponse paymentResponse;

    @XmlElementWrapper(name = "balances")
    @XmlElement(name = "balance")
    private List<Balance> balances;

}
