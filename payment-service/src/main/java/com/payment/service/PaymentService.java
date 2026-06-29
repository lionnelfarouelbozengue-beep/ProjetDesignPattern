package com.payment.service;

import com.payment.model.Payment;
import com.payment.provider.PaymentProvider;
import com.payment.provider.PaymentProviderFactory;
import com.payment.provider.dto.PaymentRequest;
import com.payment.provider.dto.PaymentResponse;
import com.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentProviderFactory providerFactory;
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentProviderFactory providerFactory, PaymentRepository paymentRepository) {
        this.providerFactory = providerFactory;
        this.paymentRepository = paymentRepository;
    }

    public PaymentResponse processPayment(PaymentRequest request) {
        PaymentProvider provider = providerFactory.getProvider(request.getProvider());
        PaymentResponse response = provider.pay(request);

        Payment payment = new Payment(
                request.getProvider(),
                request.getPhoneNumber(),
                request.getAmount(),
                request.getReference(),
                response.getTransactionReference(),
                response.isSuccess()
        );
        paymentRepository.save(payment);

        return response;
    }
}