package com.badwallet.fee;

import com.badwallet.user.UserRole;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class FeeCalculationServiceTest {

    @Test
    void doitAppliquerReductionPremium() {
        FeeCalculationService service = new FeeCalculationService(Map.of("WITHDRAWAL", new WithdrawalFeeStrategy()));

        BigDecimal fee = service.computeFees("WITHDRAWAL", new BigDecimal("1000"), UserRole.PREMIUM);

        assertEquals(new BigDecimal("5.0"), fee);
    }

    @Test
    void doitAnnulerFraisPourVip() {
        FeeCalculationService service = new FeeCalculationService(Map.of("WITHDRAWAL", new WithdrawalFeeStrategy()));

        BigDecimal fee = service.computeFees("WITHDRAWAL", new BigDecimal("1000"), UserRole.VIP);

        assertEquals(0, fee.compareTo(BigDecimal.ZERO));
    }
}