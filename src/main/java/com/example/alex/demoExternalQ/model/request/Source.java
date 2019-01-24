package com.example.alex.demoExternalQ.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "from")
@XmlAccessorType(XmlAccessType.FIELD)
public class Source {

    @XmlElement(name = "ccy")
    private String currency;

    @XmlElement(name = "amount")
    private BigDecimal amount;

    public Source(Currency currency) {
        this.currency = currency != null ? currency.getCurrencyCode() : null;
    }
}
