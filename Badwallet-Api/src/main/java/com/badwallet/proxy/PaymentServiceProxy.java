package com.badwallet.proxy;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class PaymentServiceProxy {

    private final PaymentServiceClient client;

    public PaymentServiceProxy(PaymentServiceClient client) {
        this.client = client;
    }

    public Response pay(String provider, String phoneNumber, BigDecimal amount, String reference) {
        return client.pay(provider, phoneNumber, amount, reference);
    }

    public Object[] getUnpaidInvoices(String phoneNumber) {
        return client.getUnpaidInvoices(phoneNumber);
    }
}