package com.badwallet.transaction;

import java.math.BigDecimal;

public abstract class AbstractTransaction {

    // Template method : squelette figé
    public final TransactionResult execute(TransactionContext context) {
        try {
            validate(context);
            checkBalance(context);
            BigDecimal result = doExecute(context);
            log(context, TransactionStatus.SUCCESS);
            return new TransactionResult(TransactionStatus.SUCCESS, result, null);
        } catch (RuntimeException e) {
            rollback(context);
            log(context, TransactionStatus.FAILED);
            return new TransactionResult(TransactionStatus.FAILED, null, e.getMessage());
        }
    }

    protected void validate(TransactionContext context) {
        if (context.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le montant doit être positif");
        }
    }

    protected abstract void checkBalance(TransactionContext context);

    protected abstract BigDecimal doExecute(TransactionContext context);

    protected void rollback(TransactionContext context) {
        System.out.println("Rollback générique pour : " + context.getWalletId());
    }

    protected void log(TransactionContext context, TransactionStatus status) {
        System.out.println("[LOG] " + context.getOperationType() + " - " + status);
    }
}