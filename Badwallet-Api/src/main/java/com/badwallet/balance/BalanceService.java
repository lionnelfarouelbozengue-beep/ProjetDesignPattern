package com.badwallet.balance;

import com.badwallet.audit.AuditPublisher;
import com.badwallet.wallet.WalletRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class BalanceService {

    private final WalletRepository walletRepository;
    private final AuditPublisher auditPublisher;

    public BalanceService(WalletRepository walletRepository, AuditPublisher auditPublisher) {
        this.walletRepository = walletRepository;
        this.auditPublisher = auditPublisher;
    }

    @Cacheable(value = "balances", key = "#phone")
    public BigDecimal getBalance(String phone) {
        auditPublisher.publishSensitiveAccess(phone, "Consultation du solde");
        return walletRepository.findBalanceByPhone(phone);
    }
}