package com.badwallet.fee;

import com.badwallet.user.UserRole;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component("PAYMENT")
public class PaymentFeeStrategy implements FeeStrategy {
    private static final BigDecimal PERCENTAGE = new BigDecimal("0.02");
    private static final BigDecimal FLAT_FEE = new BigDecimal("100");
    private static final BigDecimal THRESHOLD = new BigDecimal("5000");

    @Override
    public BigDecimal calculateFee(BigDecimal amount, UserRole role) {
        // En dessous du seuil : forfait. Au-dessus : pourcentage.
        if (amount.compareTo(THRESHOLD) < 0) {
            return FLAT_FEE;
        }
        return amount.multiply(PERCENTAGE);
    }
}