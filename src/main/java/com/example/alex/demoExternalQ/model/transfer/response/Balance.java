package com.example.alex.demoExternalQ.model.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "balance")
@XmlAccessorType(XmlAccessType.FIELD)
public class Balance {

    @XmlAttribute(name = "code")
    private String code;

    @XmlValue
    private BigDecimal value;
}
