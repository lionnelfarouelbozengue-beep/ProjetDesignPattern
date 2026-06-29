package com.badwallet.wallet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    
    @Transactional
    public Wallet createWallet(Wallet wallet) {
        // Vérifier si le téléphone existe déjà
        if (walletRepository.existsByPhoneNumber(wallet.getPhoneNumber())) {
            throw new RuntimeException("Wallet already exists for this phone number");
        }
        
        // Générer un code unique
        wallet.setCode(generateWalletCode());
        
        // Sauvegarder
        return walletRepository.save(wallet);
    }
    
    private String generateWalletCode() {
        return "WLT-" + System.currentTimeMillis();
    }
}