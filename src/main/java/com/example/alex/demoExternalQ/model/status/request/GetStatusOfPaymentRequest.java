package com.example.alex.demoExternalQ.model.status.request;

import com.example.alex.demoExternalQ.model.transfer.request.RequestWithExtraPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetStatusOfPaymentRequest extends RequestWithExtraPassword {

    @XmlElement(name = "request-type")
    private String requestType;

    @XmlElement(name = "terminal-id")
    private Long terminalId;

    @XmlElementWrapper(name = "status")
    @XmlElement(name = "payment")
    private List<PaymentForStatus> payment;

}
