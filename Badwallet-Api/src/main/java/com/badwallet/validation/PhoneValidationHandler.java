package com.badwallet.validation;

public class PhoneValidationHandler extends AbstractValidationHandler {
    private static final String PHONE_REGEX = "^7[0-9]{8}$";

    @Override
    protected ValidationResult doValidate(ValidationRequest request) {
        if (request.getPhoneNumber() == null || !request.getPhoneNumber().matches(PHONE_REGEX)) {
            return ValidationResult.failure("Format de téléphone invalide");
        }
        return ValidationResult.success();
    }
}