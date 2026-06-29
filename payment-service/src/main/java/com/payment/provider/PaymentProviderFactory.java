package com.payment.provider;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PaymentProviderFactory {

    private final Map<String, PaymentProvider> providers;

    // Spring injecte automatiquement tous les beans PaymentProvider ici
    public PaymentProviderFactory(List<PaymentProvider> providerList) {
        this.providers = providerList.stream()
                .collect(Collectors.toMap(PaymentProvider::getProviderCode, p -> p));
    }

    public PaymentProvider getProvider(String providerCode) {
        PaymentProvider provider = providers.get(providerCode.toUpperCase());
        if (provider == null) {
            throw new IllegalArgumentException("Fournisseur inconnu : " + providerCode);
        }
        return provider;
    }

    public List<String> getAvailableProviders() {
        return providers.keySet().stream().sorted().toList();
    }
}