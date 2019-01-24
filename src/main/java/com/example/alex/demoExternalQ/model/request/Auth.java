package com.example.alex.demoExternalQ.model.request;

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
@XmlRootElement(name = "auth")
@XmlAccessorType(XmlAccessType.FIELD)
public class Auth {

    @XmlElement(name = "payment")
    private PaymentRequest payment;
}
