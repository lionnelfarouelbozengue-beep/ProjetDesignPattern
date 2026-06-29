package com.badwallet.transaction;

import java.math.BigDecimal;

public class TransactionContext {
    private final String walletId;
    private final BigDecimal amount;
    private BigDecimal walletBalance;
    private final String operationType;
    private String phoneNumber;
    private String targetWalletId; // utilisé pour les transferts
    private String provider;       // utilisé pour les paiements

    public TransactionContext(String walletId, BigDecimal amount, BigDecimal walletBalance, String operationType) {
        this.walletId = walletId;
        this.amount = amount;
        this.walletBalance = walletBalance;
        this.operationType = operationType;
    }

    public String getWalletId() { return walletId; }
    public BigDecimal getAmount() { return amount; }
    public BigDecimal getWalletBalance() { return walletBalance; }
    public void setWalletBalance(BigDecimal walletBalance) { this.walletBalance = walletBalance; }
    public String getOperationType() { return operationType; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getTargetWalletId() { return targetWalletId; }
    public void setTargetWalletId(String targetWalletId) { this.targetWalletId = targetWalletId; }
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
}