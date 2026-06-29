package com.badwallet.transaction;

import com.badwallet.audit.AuditPublisher;
import com.badwallet.fee.FeeCalculationService;
import com.badwallet.proxy.PaymentServiceProxy;
import com.badwallet.proxy.Response;
import com.badwallet.transaction.dto.*;
import com.badwallet.user.UserRole;
import com.badwallet.wallet.Wallet;
import com.badwallet.wallet.WalletService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Map;

@Service
public class TransactionService {

    private final Map<TransactionType, AbstractTransaction> transactions;
    private final WalletService walletService;
    private final FeeCalculationService feeCalculationService;
    private final AuditPublisher auditPublisher;
    private final PaymentServiceProxy paymentServiceProxy;

    public TransactionService(WithdrawalTransaction withdrawalTransaction,
                               DepositTransaction depositTransaction,
                               TransferTransaction transferTransaction,
                               PaymentTransaction paymentTransaction,
                               WalletService walletService,
                               FeeCalculationService feeCalculationService,
                               AuditPublisher auditPublisher,
                               PaymentServiceProxy paymentServiceProxy) {
        this.transactions = Map.of(
                TransactionType.WITHDRAWAL, withdrawalTransaction,
                TransactionType.DEPOSIT, depositTransaction,
                TransactionType.TRANSFER, transferTransaction,
                TransactionType.PAYMENT, paymentTransaction
        );
        this.walletService = walletService;
        this.feeCalculationService = feeCalculationService;
        this.auditPublisher = auditPublisher;
        this.paymentServiceProxy = paymentServiceProxy;
    }

    public TransactionResult deposit(DepositRequest request) {
        Wallet wallet = walletService.getWalletByPhone(request.getPhone());
        TransactionContext context = new TransactionContext(
                wallet.getPhone(), request.getAmount(), wallet.getBalance(), "DEPOSIT");

        TransactionResult result = transactions.get(TransactionType.DEPOSIT).execute(context);
        finalizeResult(wallet, result);
        return result;
    }

    public TransactionResult withdraw(WithdrawRequest request) {
        Wallet wallet = walletService.getWalletByPhone(request.getPhone());
        BigDecimal fee = feeCalculationService.computeFees("WITHDRAWAL", request.getAmount(), wallet.getRole());
        BigDecimal totalDebit = request.getAmount().add(fee);

        TransactionContext context = new TransactionContext(
                wallet.getPhone(), totalDebit, wallet.getBalance(), "WITHDRAWAL");

        TransactionResult result = transactions.get(TransactionType.WITHDRAWAL).execute(context);
        finalizeResult(wallet, result);
        return result;
    }

    public TransactionResult transfer(TransferRequest request) {
        Wallet source = walletService.getWalletByPhone(request.getSourcePhone());
        Wallet target = walletService.getWalletByPhone(request.getTargetPhone());

        BigDecimal fee = feeCalculationService.computeFees("TRANSFER", request.getAmount(), source.getRole());
        BigDecimal totalDebit = request.getAmount().add(fee);

        TransactionContext context = new TransactionContext(
                source.getPhone(), totalDebit, source.getBalance(), "TRANSFER");
        context.setTargetWalletId(target.getPhone());

        TransactionResult result = transactions.get(TransactionType.TRANSFER).execute(context);

        if (result.getStatus() == TransactionStatus.SUCCESS) {
            walletService.updateBalance(source.getPhone(), result.getNewBalance());
            walletService.updateBalance(target.getPhone(), target.getBalance().add(request.getAmount()));
            auditPublisher.publishSuccess(source.getPhone(), "Transfert de " + request.getAmount() + " vers " + target.getPhone());
        } else {
            auditPublisher.publishFailure(source.getPhone(), result.getErrorMessage());
        }

        return result;
    }

    public TransactionResult pay(PayRequest request) {
        Wallet wallet = walletService.getWalletByPhone(request.getPhone());
        BigDecimal fee = feeCalculationService.computeFees("PAYMENT", request.getAmount(), wallet.getRole());
        BigDecimal totalDebit = request.getAmount().add(fee);

        TransactionContext context = new TransactionContext(
                wallet.getPhone(), totalDebit, wallet.getBalance(), "PAYMENT");
        context.setProvider(request.getProvider());

        TransactionResult result = transactions.get(TransactionType.PAYMENT).execute(context);

        if (result.getStatus() == TransactionStatus.SUCCESS) {
            Response paymentResponse = paymentServiceProxy.pay(
                    request.getProvider(), request.getPhone(), request.getAmount(), request.getReference());

            if (!paymentResponse.isSuccess()) {
                // Le débit local a réussi mais le paiement externe a échoué : on annule
                return new TransactionResult(TransactionStatus.FAILED, null, paymentResponse.getMessage());
            }
        }

        finalizeResult(wallet, result);
        return result;
    }

    private void finalizeResult(Wallet wallet, TransactionResult result) {
        if (result.getStatus() == TransactionStatus.SUCCESS) {
            walletService.updateBalance(wallet.getPhone(), result.getNewBalance());
            auditPublisher.publishSuccess(wallet.getPhone(), "Opération réussie, nouveau solde : " + result.getNewBalance());
        } else {
            auditPublisher.publishFailure(wallet.getPhone(), result.getErrorMessage());
        }
    }
}