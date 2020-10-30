package com.bachelor.microservice2.exception;

public class UserIsAlreadySubscribedToGym extends RuntimeException{
    public UserIsAlreadySubscribedToGym() {
        super("User is already subscribed to this gym!");
    }
}
