package com.badwallet.transaction;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DepositTransaction extends AbstractTransaction {

    @Override
    protected void checkBalance(TransactionContext context) {
        // Un dépôt n'a pas besoin de vérifier le solde existant
    }

    @Override
    protected BigDecimal doExecute(TransactionContext context) {
        return context.getWalletBalance().add(context.getAmount());
    }

    @Override
    protected void rollback(TransactionContext context) {
        System.out.println("Rollback dépôt : aucun crédit appliqué pour " + context.getWalletId());
    }
}