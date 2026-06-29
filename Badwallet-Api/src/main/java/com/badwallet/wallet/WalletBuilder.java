package com.badwallet.wallet;

import com.badwallet.user.UserRole;
import java.math.BigDecimal;

public class WalletBuilder {

    private String phone;
    private String email;
    private BigDecimal balance = BigDecimal.ZERO;
    private String code;
    private String currency = "XOF";
    private UserRole role = UserRole.STANDARD;

    public WalletBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public WalletBuilder email(String email) {
        this.email = email;
        return this;
    }

    public WalletBuilder balance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public WalletBuilder code(String code) {
        this.code = code;
        return this;
    }

    public WalletBuilder currency(String currency) {
        this.currency = currency;
        return this;
    }

    public WalletBuilder role(UserRole role) {
        this.role = role;
        return this;
    }

    // Configurations prédéfinies
    public WalletBuilder premiumWallet() {
        this.role = UserRole.PREMIUM;
        this.balance = new BigDecimal("10000");
        return this;
    }

    public WalletBuilder standardWallet() {
        this.role = UserRole.STANDARD;
        this.balance = BigDecimal.ZERO;
        return this;
    }

    public WalletBuilder vipWallet() {
        this.role = UserRole.VIP;
        this.balance = new BigDecimal("50000");
        return this;
    }

    public Wallet build() {
        if (phone == null || code == null) {
            throw new IllegalStateException("phone et code sont obligatoires");
        }
        return new Wallet(this);
    }

    // Getters utilisés par le constructeur de Wallet
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public BigDecimal getBalance() { return balance; }
    public String getCode() { return code; }
    public String getCurrency() { return currency; }
    public UserRole getRole() { return role; }
}