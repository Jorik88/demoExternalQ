package com.example.alex.demoExternalQ.model.status.response;

import com.example.alex.demoExternalQ.model.transfer.response.Balance;
import com.example.alex.demoExternalQ.model.transfer.response.BasePaymentResponse;
import com.example.alex.demoExternalQ.model.transfer.response.ResultCode;
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
public class GetStatusOfPaymentResponse {

    @XmlElement(name = "result-code")
    private ResultCode resultCode;

    @XmlElement(name = "payment")
    private BasePaymentResponse payment;

    @XmlElementWrapper(name = "balances")
    @XmlElement(name = "balance")
    private List<Balance> balance;

}
