package com.example.alex.demoExternalQ.model.transfer.response;

import com.example.alex.demoExternalQ.model.transfer.request.Destination;
import com.example.alex.demoExternalQ.model.transfer.request.Source;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "payment")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionPaymentResponse extends BasePaymentResponse {

    @XmlElement(name = "from")
    private Source source;

    @XmlElement(name = "to")
    private Destination destination;

}
