package com.example.alex.demoExternalQ.model.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "extra")
@XmlAccessorType(XmlAccessType.FIELD)
public class Extra {

    @XmlAttribute(name = "name")
    private String name = "password";

    @XmlValue
    private String value;
}
