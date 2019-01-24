package com.example.alex.demoExternalQ.model.response;

import com.example.alex.demoExternalQ.model.request.Destination;
import com.example.alex.demoExternalQ.model.request.Source;
import com.example.alex.demoExternalQ.utils.DateAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "payment")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentResponse {

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

    @XmlElement(name = "from")
    private Source source;

    @XmlElement(name = "to")
    private Destination destination;

}
