package com.badwallet.fee;

import com.badwallet.fee.decorators.PremiumDiscountDecorator;
import com.badwallet.fee.decorators.VipDiscountDecorator;
import com.badwallet.user.UserRole;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Map;

@Service
public class FeeCalculationService {

    private final Map<String, FeeStrategy> baseStrategies;

    public FeeCalculationService(Map<String, FeeStrategy> baseStrategies) {
        this.baseStrategies = baseStrategies;
    }

    public BigDecimal computeFees(String operationType, BigDecimal amount, UserRole role) {
        FeeStrategy base = baseStrategies.get(operationType);
        if (base == null) {
            throw new IllegalArgumentException("Type d'opération inconnu : " + operationType);
        }
        // VIP d'abord (annule tout), puis Premium si pas VIP
        FeeStrategy decorated = new VipDiscountDecorator(new PremiumDiscountDecorator(base));
        return decorated.calculateFee(amount, role);
    }
}