package com.badwallet.wallet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WalletServiceTest {

    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private WalletService walletService;

    @Test
    void doitCreerUnWalletPremium() {
        when(walletRepository.save(any(Wallet.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Wallet wallet = walletService.createPremiumWallet("771234567", "1234");

        assertEquals("771234567", wallet.getPhone());
        verify(walletRepository, times(1)).save(any(Wallet.class));
    }

    @Test
    void doitLeverExceptionSiWalletIntrouvable() {
        when(walletRepository.findByPhone("000")).thenReturn(Optional.empty());

        assertThrows(com.badwallet.exception.WalletNotFoundException.class,
                () -> walletService.getWalletByPhone("000"));
    }
}