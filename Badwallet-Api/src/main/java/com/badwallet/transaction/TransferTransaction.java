package com.badwallet.transaction;

import com.badwallet.exception.InsufficientBalanceException;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class TransferTransaction extends AbstractTransaction {

    @Override
    protected void validate(TransactionContext context) {
        super.validate(context);
        if (context.getTargetWalletId() == null) {
            throw new IllegalArgumentException("Le wallet destinataire est obligatoire");
        }
    }

    @Override
    protected void checkBalance(TransactionContext context) {
        if (context.getWalletBalance().compareTo(context.getAmount()) < 0) {
            throw new InsufficientBalanceException("Solde insuffisant pour le transfert");
        }
    }

    @Override
    protected BigDecimal doExecute(TransactionContext context) {
        // Débit côté émetteur ; le crédit du destinataire est géré par le service appelant
        return context.getWalletBalance().subtract(context.getAmount());
    }

    @Override
    protected void rollback(TransactionContext context) {
        System.out.println("Rollback transfert entre " + context.getWalletId() + " et " + context.getTargetWalletId());
    }
}