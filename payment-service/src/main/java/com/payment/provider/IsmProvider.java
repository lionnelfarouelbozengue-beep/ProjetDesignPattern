package com.payment.provider;

import com.payment.provider.dto.PaymentRequest;
import com.payment.provider.dto.PaymentResponse;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class IsmProvider implements PaymentProvider {

    @Override
    public PaymentResponse pay(PaymentRequest request) {
        // Appel spécifique à l'API ISM (simulé)
        System.out.println("Appel API ISM pour " + request.getPhoneNumber() + " - " + request.getAmount());
        String txRef = "ISM-" + UUID.randomUUID();
        return new PaymentResponse(true, txRef, "Paiement ISM réussi");
    }

    @Override
    public String getProviderCode() {
        return "ISM";
    }
}