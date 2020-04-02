package com.bachelor.microservice2.exception;

public class UserDoesNotExist extends RuntimeException{
    public UserDoesNotExist() {
        super("User does not exist!");
    }
}
