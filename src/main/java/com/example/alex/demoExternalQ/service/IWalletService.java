package com.example.alex.demoExternalQ.service;

import com.example.alex.demoExternalQ.enums.TransactionStatus;
import com.example.alex.demoExternalQ.model.base.BalanceItem;
import com.example.alex.demoExternalQ.model.base.TotalBalanceResponse;
import com.example.alex.demoExternalQ.model.base.TransferRequest;
import org.apache.commons.lang3.StringUtils;

public interface IWalletService {
    default BalanceItem getBalance(String walletId) {
        try {
            TotalBalanceResponse totalBalanceResponse = getTotalBalance();
            for (BalanceItem balanceItem: totalBalanceResponse.getBalances()) {
                if (StringUtils.equals(walletId, balanceItem.getWalletId())) {
                    return balanceItem;
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        throw new IllegalArgumentException();
    }

    TotalBalanceResponse getTotalBalance();
    String transferExternal(TransferRequest transferRequest);
    TransactionStatus getTransactionStatus(String transactionId);
    String getPaymentSystemCode();
}
