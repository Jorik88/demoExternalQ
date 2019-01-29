package com.example.alex.demoExternalQ.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "qiwi")
public class QiwiConfiguration {

    private Long terminalId;
    private String password;
    private Long accountNumber;
}
