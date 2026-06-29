package com.payment.provider;

import com.payment.provider.dto.PaymentRequest;
import com.payment.provider.dto.PaymentResponse;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class WaveProvider implements PaymentProvider {

    @Override
    public PaymentResponse pay(PaymentRequest request) {
        // Appel spécifique à l'API WAVE (simulé)
        System.out.println("Appel API WAVE pour " + request.getPhoneNumber() + " - " + request.getAmount());
        String txRef = "WAVE-" + UUID.randomUUID();
        return new PaymentResponse(true, txRef, "Paiement WAVE réussi");
    }

    @Override
    public String getProviderCode() {
        return "WAVE";
    }
}