package com.example.alex.demoExternalQ.model.request;

import lombok.Setter;
import javax.xml.bind.annotation.XmlElement;

@Setter
public abstract class RequestWithExtraPassword {

    @XmlElement(name = "extra")
    private Extra extra;
}
