package com.payment.controller;

import com.payment.provider.dto.PaymentRequest;
import com.payment.provider.dto.PaymentResponse;
import com.payment.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    @Test
    void doitRetournerLaReponseDuService() {
        PaymentRequest request = new PaymentRequest("WAVE", "771234567", new BigDecimal("1000"), "REF123");
        PaymentResponse expectedResponse = new PaymentResponse(true, "WAVE-abc123", "Paiement WAVE réussi");

        when(paymentService.processPayment(request)).thenReturn(expectedResponse);

        ResponseEntity<PaymentResponse> result = paymentController.pay(request);

        assertEquals(200, result.getStatusCode().value());
        assertTrue(result.getBody().isSuccess());
        verify(paymentService, times(1)).processPayment(request);
    }
}