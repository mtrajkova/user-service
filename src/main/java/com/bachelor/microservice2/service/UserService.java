package com.bachelor.microservice2.service;

import com.bachelor.microservice2.model.User;
import com.bachelor.microservice2.model.dto.GymDto;
import com.bachelor.microservice2.model.dto.OfferDto;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    List<GymDto> getAllGymsForUser(Long userId);

    List<OfferDto> getAllOffersForUser(Long userId);
}
