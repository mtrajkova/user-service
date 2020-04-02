package com.bachelor.microservice2.service.impl;

import com.bachelor.microservice2.exception.UserDoesNotExist;
import com.bachelor.microservice2.model.GymSubscription;
import com.bachelor.microservice2.model.OfferSubscription;
import com.bachelor.microservice2.model.User;
import com.bachelor.microservice2.model.dto.GymDto;
import com.bachelor.microservice2.model.dto.OfferDto;
import com.bachelor.microservice2.repository.GymSubscriptionRepository;
import com.bachelor.microservice2.repository.OfferSubscriptionRepository;
import com.bachelor.microservice2.repository.UserRepository;
import com.bachelor.microservice2.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GymSubscriptionRepository gymSubscriptionRepository;
    private final OfferSubscriptionRepository offerSubscriptionRepository;

    public UserServiceImpl(UserRepository userRepository, GymSubscriptionRepository gymSubscriptionRepository, OfferSubscriptionRepository offerSubscriptionRepository) {
        this.userRepository = userRepository;
        this.gymSubscriptionRepository = gymSubscriptionRepository;
        this.offerSubscriptionRepository = offerSubscriptionRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<GymDto> getAllGymsForUser(Long userId) {
        List<Long> gymIds = getSubscribedGymIdsForUser(userId);
        return getGymDetails(gymIds);
    }

    @Override
    public List<OfferDto> getAllOffersForUser(Long userId) {
        List<Long> offerIds = getSubscribedOfferIdsForUser(userId);
        return getOfferDetails(offerIds);
    }

    private List<Long> getSubscribedGymIdsForUser(Long userId) {
        userRepository.findById(userId).orElseThrow(UserDoesNotExist::new);
        return gymSubscriptionRepository.findAllByUserId(userId).stream()
                .map(GymSubscription::getGymId)
                .collect(Collectors.toList());
    }

    private List<GymDto> getGymDetails(List<Long> gymIds) {
        //TODO call gym-offers service
        return null;
    }

    private List<Long> getSubscribedOfferIdsForUser(Long userId) {
        userRepository.findById(userId).orElseThrow(UserDoesNotExist::new);
        return offerSubscriptionRepository.findAllByUserIdAndOfferValidIsTrue(userId).stream()
                .map(OfferSubscription::getOfferId)
                .collect(Collectors.toList());
    }

    private List<OfferDto> getOfferDetails(List<Long> offerIds) {
        //TODO call gym-offers service
        return null;
    }
}
