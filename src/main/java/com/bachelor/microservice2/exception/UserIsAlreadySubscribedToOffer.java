package com.bachelor.microservice2.exception;

public class UserIsAlreadySubscribedToOffer extends RuntimeException{
    public UserIsAlreadySubscribedToOffer() {
        super("User is already subscribed to this offer!");
    }
}
