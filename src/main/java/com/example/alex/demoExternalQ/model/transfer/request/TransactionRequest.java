package com.example.alex.demoExternalQ.model.transfer.request;

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
public class TransactionRequest extends RequestWithExtraPassword {

    @XmlElement(name = "request-type")
    private String requestType;

    @XmlElement(name = "terminal-id")
    private Long terminalId;

    @XmlElement(name = "auth")
    private Auth auth;

}
