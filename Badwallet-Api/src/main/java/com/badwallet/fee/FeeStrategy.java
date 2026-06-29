package com.badwallet.fee;

import com.badwallet.user.UserRole;
import java.math.BigDecimal;

public interface FeeStrategy {
    BigDecimal calculateFee(BigDecimal amount, UserRole role);
}