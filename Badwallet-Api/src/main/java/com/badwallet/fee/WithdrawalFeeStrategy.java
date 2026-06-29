package com.badwallet.fee;

import com.badwallet.user.UserRole;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("WITHDRAWAL")
public class WithdrawalFeeStrategy implements FeeStrategy {
    private static final BigDecimal PERCENTAGE = new BigDecimal("0.01");
    private static final BigDecimal MAX_FEE = new BigDecimal("5000");

    @Override
    public BigDecimal calculateFee(BigDecimal amount, UserRole role) {
        return amount.multiply(PERCENTAGE).min(MAX_FEE);
    }
}