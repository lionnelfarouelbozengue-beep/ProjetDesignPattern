package com.badwallet.validation;

import java.math.BigDecimal;

public class AmountValidationHandler extends AbstractValidationHandler {
    @Override
    protected ValidationResult doValidate(ValidationRequest request) {
        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return ValidationResult.failure("Le montant doit être positif");
        }
        return ValidationResult.success();
    }
}