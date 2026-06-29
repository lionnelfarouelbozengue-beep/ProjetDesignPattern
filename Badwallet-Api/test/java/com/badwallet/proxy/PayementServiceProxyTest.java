package com.badwallet.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PaymentServiceProxyTest {

    @Test
    void doitDelegueAuClientEtRetournerLaReponse() {
        RestTemplate mockRestTemplate = mock(RestTemplate.class);
        Response fakeResponse = new Response(true, "REF123", "OK");
        when(mockRestTemplate.postForObject(anyString(), any(), eq(Response.class))).thenReturn(fakeResponse);

        PaymentServiceClient client = new PaymentServiceClient(mockRestTemplate);
        PaymentServiceProxy proxy = new PaymentServiceProxy(client);

        Response response = proxy.pay("WAVE", "771234567", new BigDecimal("1000"), "REF123");

        assertTrue(response.isSuccess());
    }
}