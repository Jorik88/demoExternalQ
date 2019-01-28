package com.example.alex.demoExternalQ.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "result-code")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResultCode {

    @XmlAttribute(name = "fatal")
    private boolean fatal;

    @XmlValue
    private int codeValue;
}
