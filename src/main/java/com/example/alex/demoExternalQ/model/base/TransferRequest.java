package com.example.alex.demoExternalQ.model.base;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransferRequest {

    private String transferRequestId;
    private String paymentSystem;
    private String currency;
    private String targetWallet;
    private BigDecimal amount;
    private boolean taxFromSender;
}
