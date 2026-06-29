package com.badwallet.wallet;

import com.badwallet.user.UserRole;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestParam String phone,
                                                @RequestParam String email,
                                                @RequestParam String code,
                                                @RequestParam(defaultValue = "STANDARD") UserRole role) {
        Wallet wallet = walletService.createWallet(phone, email, code, role);
        return ResponseEntity.ok(wallet);
    }

    @PostMapping("/premium")
    public ResponseEntity<Wallet> createPremiumWallet(@RequestParam String phone, @RequestParam String code) {
        Wallet wallet = walletService.createPremiumWallet(phone, code);
        return ResponseEntity.ok(wallet);
    }

    @GetMapping("/{phone}")
    public ResponseEntity<Wallet> getWallet(@PathVariable String phone) {
        return ResponseEntity.ok(walletService.getWalletByPhone(phone));
    }
}