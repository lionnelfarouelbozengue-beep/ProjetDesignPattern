package com.badwallet.validation;

import org.springframework.stereotype.Component;

@Component
public class ValidationChainFactory {

    public ValidationHandler buildPaymentChain() {
        ValidationHandler amount = new AmountValidationHandler();
        amount
                .setNext(new BalanceValidationHandler())
                .setNext(new PhoneValidationHandler())
                .setNext(new ServiceValidationHandler());
        return amount;
    }

    public ValidationHandler buildTransferChain() {
        ValidationHandler amount = new AmountValidationHandler();
        amount
                .setNext(new BalanceValidationHandler())
                .setNext(new PhoneValidationHandler());
        return amount;
    }

    public ValidationHandler buildWithdrawalChain() {
        ValidationHandler amount = new AmountValidationHandler();
        amount.setNext(new BalanceValidationHandler());
        return amount;
    }
}