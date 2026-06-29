package com.badwallet.fee.decorators;

import com.badwallet.fee.FeeStrategy;
import com.badwallet.user.UserRole;
import java.math.BigDecimal;

public abstract class FeeDecorator implements FeeStrategy {

    protected final FeeStrategy delegate;

    protected FeeDecorator(FeeStrategy delegate) {
        this.delegate = delegate;
    }

    @Override
    public BigDecimal calculateFee(BigDecimal amount, UserRole role) {
        return delegate.calculateFee(amount, role);
    }
}