package com.badwallet.transaction.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class PayRequest {

    @NotNull
    private String phone;

    @NotNull
    private String provider;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private String reference;

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
}