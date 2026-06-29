package com.badwallet.validation;

import java.math.BigDecimal;

public class ValidationRequest {
    private final BigDecimal amount;
    private final BigDecimal availableBalance;
    private final String phoneNumber;
    private final boolean serviceAvailable;

    public ValidationRequest(BigDecimal amount, BigDecimal availableBalance, String phoneNumber, boolean serviceAvailable) {
        this.amount = amount;
        this.availableBalance = availableBalance;
        this.phoneNumber = phoneNumber;
        this.serviceAvailable = serviceAvailable;
    }

    public BigDecimal getAmount() { return amount; }
    public BigDecimal getAvailableBalance() { return availableBalance; }
    public String getPhoneNumber() { return phoneNumber; }
    public boolean isServiceAvailable() { return serviceAvailable; }
}