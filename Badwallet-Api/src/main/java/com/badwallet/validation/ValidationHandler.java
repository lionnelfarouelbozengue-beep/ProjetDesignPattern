package com.badwallet.validation;

public interface ValidationHandler {
    ValidationHandler setNext(ValidationHandler next);
    ValidationResult handle(ValidationRequest request);
}