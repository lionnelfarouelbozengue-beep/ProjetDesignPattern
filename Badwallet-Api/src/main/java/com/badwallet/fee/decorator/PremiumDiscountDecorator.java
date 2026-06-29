package com.badwallet.fee.decorators;

import com.badwallet.fee.FeeStrategy;
import com.badwallet.user.UserRole;
import java.math.BigDecimal;

public class VipDiscountDecorator extends FeeDecorator {

    // Les VIP ne paient aucun frais
    public VipDiscountDecorator(FeeStrategy delegate) {
        super(delegate);
    }

    @Override
    public BigDecimal calculateFee(BigDecimal amount, UserRole role) {
        if (role == UserRole.VIP) {
            return BigDecimal.ZERO;
        }
        return super.calculateFee(amount, role);
    }
}