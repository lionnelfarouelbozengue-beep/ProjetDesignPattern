package com.badwallet.transaction;

import com.badwallet.exception.InsufficientBalanceException;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class PaymentTransaction extends AbstractTransaction {

    @Override
    protected void validate(TransactionContext context) {
        super.validate(context);
        if (context.getProvider() == null) {
            throw new IllegalArgumentException("Le fournisseur de paiement est obligatoire");
        }
    }

    @Override
    protected void checkBalance(TransactionContext context) {
        if (context.getWalletBalance().compareTo(context.getAmount()) < 0) {
            throw new InsufficientBalanceException("Solde insuffisant pour le paiement");
        }
    }

    @Override
    protected BigDecimal doExecute(TransactionContext context) {
        return context.getWalletBalance().subtract(context.getAmount());
    }

    @Override
    protected void rollback(TransactionContext context) {
        System.out.println("Rollback paiement via " + context.getProvider() + " pour " + context.getWalletId());
    }
}