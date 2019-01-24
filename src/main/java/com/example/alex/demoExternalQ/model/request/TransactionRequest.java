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
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionRequest {

    @XmlElement(name = "request-type")
    private String requestType;

    @XmlElement(name = "terminal-id")
    private String terminalId;

    @XmlElement(name = "extra")
    private Extra extra;

    @XmlElement(name = "auth")
    private Auth auth;

}