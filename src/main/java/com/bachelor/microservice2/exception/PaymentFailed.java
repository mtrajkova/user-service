package com.bachelor.microservice2.exception;

public class PaymentFailed extends RuntimeException {
    public PaymentFailed() {
        super("Payment was not successful. Please try again later.");
    }
}
