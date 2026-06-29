package com.payment.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private String transactionReference;

    @Column(nullable = false)
    private boolean success;

    @Column(nullable = false)
    private Instant createdAt;

    protected Payment() {}

    public Payment(String provider, String phoneNumber, BigDecimal amount, String reference,
                   String transactionReference, boolean success) {
        this.provider = provider;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
        this.reference = reference;
        this.transactionReference = transactionReference;
        this.success = success;
        this.createdAt = Instant.now();
    }

    public Long getId() { return id; }
    public String getProvider() { return provider; }
    public String getPhoneNumber() { return phoneNumber; }
    public BigDecimal getAmount() { return amount; }
    public String getReference() { return reference; }
    public String getTransactionReference() { return transactionReference; }
    public boolean isSuccess() { return success; }
    public Instant getCreatedAt() { return createdAt; }
}