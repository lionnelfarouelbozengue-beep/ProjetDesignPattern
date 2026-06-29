package com.badwallet.proxy;

import java.math.BigDecimal;

public class Request {
    private String provider;
    private String phoneNumber;
    private BigDecimal amount;
    private String reference;

    public Request() {}

    public Request(String provider, String phoneNumber, BigDecimal amount, String reference) {
        this.provider = provider;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.reference = reference;
    }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
}