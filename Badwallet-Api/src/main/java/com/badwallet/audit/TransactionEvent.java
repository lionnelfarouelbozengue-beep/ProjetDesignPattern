package com.badwallet.audit;

import java.time.Instant;

public class TransactionEvent {
    private final String eventType; // SUCCESS, FAILED, ACCESS
    private final String walletId;
    private final String details;
    private final Instant timestamp;

    public TransactionEvent(String eventType, String walletId, String details) {
        this.eventType = eventType;
        this.walletId = walletId;
        this.details = details;
        this.timestamp = Instant.now();
    }

    public String getEventType() { return eventType; }
    public String getWalletId() { return walletId; }
    public String getDetails() { return details; }
    public Instant getTimestamp() { return timestamp; }
}