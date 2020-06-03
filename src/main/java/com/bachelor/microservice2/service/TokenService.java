package com.bachelor.microservice2.service;

import com.bachelor.microservice2.model.UserPrincipal;

public interface TokenService {
    UserPrincipal parseToken(String token);
}
