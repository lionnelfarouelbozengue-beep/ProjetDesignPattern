package com.badwallet.transaction.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class TransferRequest {

    @NotNull
    private String sourcePhone;

    @NotNull
    private String targetPhone;

    @NotNull
    @Positive
    private BigDecimal amount;

    public String getSourcePhone() { return sourcePhone; }
    public void setSourcePhone(String sourcePhone) { this.sourcePhone = sourcePhone; }
    public String getTargetPhone() { return targetPhone; }
    public void setTargetPhone(String targetPhone) { this.targetPhone = targetPhone; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}