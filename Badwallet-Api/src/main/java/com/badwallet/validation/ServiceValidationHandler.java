package com.badwallet.validation;

public class ServiceValidationHandler extends AbstractValidationHandler {
    @Override
    protected ValidationResult doValidate(ValidationRequest request) {
        if (!request.isServiceAvailable()) {
            return ValidationResult.failure("Service de paiement indisponible");
        }
        return ValidationResult.success();
    }
}