package com.badwallet.transaction;

import com.badwallet.exception.InsufficientBalanceException;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class WithdrawalTransaction extends AbstractTransaction {

    @Override
    protected void checkBalance(TransactionContext context) {
        if (context.getWalletBalance().compareTo(context.getAmount()) < 0) {
            throw new InsufficientBalanceException("Solde insuffisant pour le retrait");
        }
    }

    @Override
    protected BigDecimal doExecute(TransactionContext context) {
        return context.getWalletBalance().subtract(context.getAmount());
    }

    @Override
    protected void rollback(TransactionContext context) {
        System.out.println("Rollback retrait : aucun débit appliqué pour " + context.getWalletId());
    }
}