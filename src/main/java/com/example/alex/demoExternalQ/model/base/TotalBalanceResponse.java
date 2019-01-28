package com.example.alex.demoExternalQ.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class TotalBalanceResponse {
    private List<BalanceItem> balances;
}
