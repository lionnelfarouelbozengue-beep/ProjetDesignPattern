package com.badwallet.validation;

public class BalanceValidationHandler extends AbstractValidationHandler {
    @Override
    protected ValidationResult doValidate(ValidationRequest request) {
        if (request.getAvailableBalance().compareTo(request.getAmount()) < 0) {
            return ValidationResult.failure("Solde insuffisant");
        }
        return ValidationResult.success();
    }
}