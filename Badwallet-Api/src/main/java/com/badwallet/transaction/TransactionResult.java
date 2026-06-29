package com.badwallet.transaction;

import java.math.BigDecimal;

public class TransactionResult {
    private final TransactionStatus status;
    private final BigDecimal newBalance;
    private final String errorMessage;

    public TransactionResult(TransactionStatus status, BigDecimal newBalance, String errorMessage) {
        this.status = status;
        this.newBalance = newBalance;
        this.errorMessage = errorMessage;
    }

    public TransactionStatus getStatus() { return status; }
    public BigDecimal getNewBalance() { return newBalance; }
    public String getErrorMessage() { return errorMessage; }
}