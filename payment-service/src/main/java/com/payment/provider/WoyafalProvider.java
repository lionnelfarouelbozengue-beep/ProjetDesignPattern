package com.payment.provider;

import com.payment.provider.dto.PaymentRequest;
import com.payment.provider.dto.PaymentResponse;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class WoyafalProvider implements PaymentProvider {

    @Override
    public PaymentResponse pay(PaymentRequest request) {
        // Appel spécifique à l'API WOYAFAL (simulé) - typiquement pour factures SENELEC
        System.out.println("Appel API WOYAFAL pour " + request.getPhoneNumber() + " - " + request.getAmount());
        String txRef = "WOYAFAL-" + UUID.randomUUID();
        return new PaymentResponse(true, txRef, "Paiement WOYAFAL réussi");
    }

    @Override
    public String getProviderCode() {
        return "WOYAFAL";
    }
}