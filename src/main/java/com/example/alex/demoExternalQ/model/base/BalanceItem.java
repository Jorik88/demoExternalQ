package com.example.alex.demoExternalQ.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BalanceItem {
    private String walletId;
    private BigDecimal balance;
}
