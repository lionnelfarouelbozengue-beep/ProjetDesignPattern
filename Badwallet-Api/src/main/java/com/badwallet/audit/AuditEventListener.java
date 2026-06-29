package com.badwallet.audit;

import jakarta.persistence.EntityManager;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AuditEventListener {

    private final EntityManager entityManager;

    public AuditEventListener(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @EventListener
    @Transactional
    public void onTransactionEvent(TransactionEvent event) {
        // Log console pour suivi en direct
        System.out.printf("[AUDIT] %s | wallet=%s | %s | %s%n",
                event.getTimestamp(), event.getWalletId(), event.getEventType(), event.getDetails());

        // Persistance pour permettre le replay en cas de crash
        EventStore entity = new EventStore(event.getEventType(), event.getWalletId(), event.getDetails());
        entityManager.persist(entity);
    }
}