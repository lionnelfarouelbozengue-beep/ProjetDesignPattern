package com.badwallet.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.math.BigDecimal;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByPhone(String phone);

    @Query("SELECT w.balance FROM Wallet w WHERE w.phone = :phone")
    BigDecimal findBalanceByPhone(String phone);

    boolean existsByPhone(String phone);
}