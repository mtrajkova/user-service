package com.bachelor.microservice2.client.payment;

import com.payment.apipayment.model.ChargeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "payment-service")
public interface PaymentServiceClient {

    @PostMapping("/create-charge")
    ResponseEntity<String> createCharge(@RequestHeader("Authorization") String jwt, @RequestHeader("token") String token,
                                        @RequestBody ChargeRequest chargeRequest);

}
