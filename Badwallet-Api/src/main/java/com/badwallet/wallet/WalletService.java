package com.badwallet.wallet;

import com.badwallet.exception.WalletNotFoundException;
import com.badwallet.user.UserRole;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(String phone, String email, String code, UserRole role) {
        Wallet wallet = Wallet.builder()
                .phone(phone)
                .email(email)
                .code(code)
                .role(role)
                .build();
        return walletRepository.save(wallet);
    }

    public Wallet createPremiumWallet(String phone, String code) {
        Wallet wallet = Wallet.builder()
                .phone(phone)
                .code(code)
                .premiumWallet()
                .build();
        return walletRepository.save(wallet);
    }

    public Wallet getWalletByPhone(String phone) {
        return walletRepository.findByPhone(phone)
                .orElseThrow(() -> new WalletNotFoundException("Wallet introuvable pour : " + phone));
    }

    @CacheEvict(value = "balances", key = "#phone")
    public Wallet updateBalance(String phone, BigDecimal newBalance) {
        Wallet wallet = getWalletByPhone(phone);
        wallet.setBalance(newBalance);
        return walletRepository.save(wallet);
    }
}