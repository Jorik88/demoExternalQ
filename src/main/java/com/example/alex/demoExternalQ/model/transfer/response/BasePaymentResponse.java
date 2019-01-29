package com.example.alex.demoExternalQ.model.transfer.response;

import com.example.alex.demoExternalQ.utils.DateAdapter;
import lombok.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "payment")
@XmlAccessorType(XmlAccessType.FIELD)
public class BasePaymentResponse {

    @XmlAttribute(name = "status")
    private String status;

    @XmlAttribute(name = "txn_id")
    private Long txnId;

    @XmlAttribute(name = "transaction-number")
    private Long transactionNumber;

    @XmlAttribute(name = "result-code")
    private int resultCode;

    @XmlAttribute(name = "final-status")
    private boolean finalStatus;

    @XmlAttribute(name = "fatal-error")
    private boolean fatalError;

    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlAttribute(name = "txn-date")
    private Date transactionDate;

}
