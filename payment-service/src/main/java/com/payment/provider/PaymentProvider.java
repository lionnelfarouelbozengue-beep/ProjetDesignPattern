package com.payment.provider;

import com.payment.provider.dto.PaymentRequest;
import com.payment.provider.dto.PaymentResponse;

public interface PaymentProvider {
    PaymentResponse pay(PaymentRequest request);
    String getProviderCode();
}