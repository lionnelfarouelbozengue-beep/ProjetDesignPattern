package com.badwallet.wallet;

import com.badwallet.user.UserRole;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String phone;

    private String email;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String currency;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    // Constructeur vide requis par JPA
    protected Wallet() {}

    Wallet(WalletBuilder builder) {
        this.phone = builder.getPhone();
        this.email = builder.getEmail();
        this.balance = builder.getBalance();
        this.code = builder.getCode();
        this.currency = builder.getCurrency();
        this.role = builder.getRole();
    }

    public static WalletBuilder builder() {
        return new WalletBuilder();
    }

    // Getters
    public Long getId() { return id; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public BigDecimal getBalance() { return balance; }
    public String getCode() { return code; }
    public String getCurrency() { return currency; }
    public UserRole getRole() { return role; }

    // Setters nécessaires pour les mises à jour (solde notamment)
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(UserRole role) { this.role = role; }
}