package com.example.alex.demoExternalQ.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "to")
@XmlAccessorType(XmlAccessType.FIELD)
public class Destination {

    @XmlElement(name = "amount")
    private BigDecimal amount;

    @XmlElement(name = "ccy")
    private String currency;

    @XmlElement(name = "service-id")
    private Long serviceId;

    @XmlElement(name = "account-number")
    private String accountNumber;
}
