package com.badwallet.audit;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "event_store")
public class EventStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;
    private String walletId;
    private String details;
    private Instant timestamp;
    private boolean replayed;

    protected EventStore() {}

    public EventStore(String eventType, String walletId, String details) {
        this.eventType = eventType;
        this.walletId = walletId;
        this.details = details;
        this.timestamp = Instant.now();
        this.replayed = false;
    }

    public Long getId() { return id; }
    public String getEventType() { return eventType; }
    public String getWalletId() { return walletId; }
    public String getDetails() { return details; }
    public Instant getTimestamp() { return timestamp; }
    public boolean isReplayed() { return replayed; }
    public void setReplayed(boolean replayed) { this.replayed = replayed; }
}