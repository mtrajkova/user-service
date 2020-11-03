package com.bachelor.microservice2.client.payment;

import com.payment.apipayment.model.ChargeRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceCaller {

    private final PaymentServiceClient paymentServiceClient;

    public PaymentServiceCaller(PaymentServiceClient paymentServiceClient) {
        this.paymentServiceClient = paymentServiceClient;
    }

    public String chargeForOffer(String token, String amount, String jwt, String email) {
        return this.paymentServiceClient.createCharge(jwt, token, new ChargeRequest(email, amount)).getBody();
    }
}
