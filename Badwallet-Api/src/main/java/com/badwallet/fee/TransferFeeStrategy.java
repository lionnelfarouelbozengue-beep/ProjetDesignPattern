package com.badwallet.fee;

import com.badwallet.user.UserRole;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("TRANSFER")
public class TransferFeeStrategy implements FeeStrategy {
    private static final BigDecimal PERCENTAGE = new BigDecimal("0.005");

    @Override
    public BigDecimal calculateFee(BigDecimal amount, UserRole role) {
        return amount.multiply(PERCENTAGE);
    }
}