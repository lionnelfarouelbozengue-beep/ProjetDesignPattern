package com.badwallet.proxy;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;

@Component
public class PaymentServiceClient {

    private final RestTemplate restTemplate;
    private static final String BASE_URL = "http://localhost:8081";

    public PaymentServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Retry(name = "paymentService")
    @CircuitBreaker(name = "paymentService", fallbackMethod = "payFallback")
    public Response pay(String provider, String phoneNumber, BigDecimal amount, String reference) {
        String url = BASE_URL + "/pay";
        Request request = new Request(provider, phoneNumber, amount, reference);
        return restTemplate.postForObject(url, request, Response.class);
    }

    public Response payFallback(String provider, String phoneNumber, BigDecimal amount, String reference, Throwable t) {
        return new Response(false, null, "Service de paiement indisponible : " + t.getMessage());
    }

    @Retry(name = "paymentService")
    @CircuitBreaker(name = "paymentService", fallbackMethod = "getInvoicesFallback")
    public Object[] getUnpaidInvoices(String phoneNumber) {
        String url = BASE_URL + "/external/factures?phone=" + phoneNumber;
        return restTemplate.getForObject(url, Object[].class);
    }

    public Object[] getInvoicesFallback(String phoneNumber, Throwable t) {
        return new Object[0];
    }
}