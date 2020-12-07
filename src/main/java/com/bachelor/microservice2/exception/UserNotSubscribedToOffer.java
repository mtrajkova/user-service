package com.bachelor.microservice2.exception;

public class UserNotSubscribedToOffer extends RuntimeException{
    public UserNotSubscribedToOffer() {
        super("User is not subscribed to this offer!");
    }
}
