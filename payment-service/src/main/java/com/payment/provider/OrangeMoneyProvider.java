package com.payment.provider;

import com.payment.provider.dto.PaymentRequest;
import com.payment.provider.dto.PaymentResponse;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class OrangeMoneyProvider implements PaymentProvider {

    @Override
    public PaymentResponse pay(PaymentRequest request) {
        // Appel spécifique à l'API ORANGE MONEY (simulé)
        System.out.println("Appel API ORANGE_MONEY pour " + request.getPhoneNumber() + " - " + request.getAmount());
        String txRef = "OM-" + UUID.randomUUID();
        return new PaymentResponse(true, txRef, "Paiement ORANGE_MONEY réussi");
    }

    @Override
    public String getProviderCode() {
        return "ORANGE_MONEY";
    }
}