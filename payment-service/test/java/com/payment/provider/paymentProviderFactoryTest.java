package com.payment.provider;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PaymentProviderFactoryTest {

    @Test
    void doitRetournerLeBonProvider() {
        PaymentProviderFactory factory = new PaymentProviderFactory(
                List.of(new IsmProvider(), new WaveProvider(), new WoyafalProvider(), new OrangeMoneyProvider())
        );

        PaymentProvider provider = factory.getProvider("WAVE");

        assertEquals("WAVE", provider.getProviderCode());
    }

    @Test
    void doitLeverExceptionSiProviderInconnu() {
        PaymentProviderFactory factory = new PaymentProviderFactory(List.of(new IsmProvider()));

        assertThrows(IllegalArgumentException.class, () -> factory.getProvider("FREE_MONEY"));
    }

    @Test
    void doitListerTousLesProvidersDisponibles() {
        PaymentProviderFactory factory = new PaymentProviderFactory(
                List.of(new IsmProvider(), new WaveProvider())
        );

        List<String> providers = factory.getAvailableProviders();

        assertEquals(2, providers.size());
        assertTrue(providers.contains("WAVE"));
    }
}