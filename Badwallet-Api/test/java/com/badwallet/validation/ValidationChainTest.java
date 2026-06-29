package com.badwallet.validation;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ValidationChainTest {

    @Test
    void doitEchouerSurMontantNegatif() {
        ValidationChainFactory factory = new ValidationChainFactory();
        ValidationHandler chain = factory.buildTransferChain();
        ValidationRequest request = new ValidationRequest(new BigDecimal("-100"), new BigDecimal("1000"), "771234567", true);

        ValidationResult result = chain.handle(request);

        assertFalse(result.isValid());
        assertEquals("Le montant doit être positif", result.getErrorMessage());
    }

    @Test
    void doitReussirSiTousLesCriteresValides() {
        ValidationChainFactory factory = new ValidationChainFactory();
        ValidationHandler chain = factory.buildTransferChain();
        ValidationRequest request = new ValidationRequest(new BigDecimal("500"), new BigDecimal("1000"), "771234567", true);

        ValidationResult result = chain.handle(request);

        assertTrue(result.isValid());
    }
}