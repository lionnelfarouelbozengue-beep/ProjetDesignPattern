package com.badwallet.audit;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AuditPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public AuditPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishSuccess(String walletId, String details) {
        eventPublisher.publishEvent(new TransactionEvent("SUCCESS", walletId, details));
    }

    public void publishFailure(String walletId, String details) {
        eventPublisher.publishEvent(new TransactionEvent("FAILED", walletId, details));
    }

    public void publishSensitiveAccess(String walletId, String details) {
        eventPublisher.publishEvent(new TransactionEvent("ACCESS", walletId, details));
    }
}