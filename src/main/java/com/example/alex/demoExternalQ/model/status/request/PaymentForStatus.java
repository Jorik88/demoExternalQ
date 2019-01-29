package com.example.alex.demoExternalQ.model.status.request;

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
public class PaymentForStatus {

    @XmlElement(name = "transaction-number")
    private Long transactionNumber;

    @XmlElement(name = "to")
    private DestinationForStatus destination;
}
