package com.badwallet.validation;

public abstract class AbstractValidationHandler implements ValidationHandler {

    private ValidationHandler next;

    @Override
    public ValidationHandler setNext(ValidationHandler next) {
        this.next = next;
        return next;
    }

    @Override
    public ValidationResult handle(ValidationRequest request) {
        ValidationResult result = doValidate(request);
        if (!result.isValid()) {
            return result;
        }
        if (next != null) {
            return next.handle(request);
        }
        return ValidationResult.success();
    }

    protected abstract ValidationResult doValidate(ValidationRequest request);
}