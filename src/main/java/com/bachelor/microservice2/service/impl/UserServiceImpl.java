package com.bachelor.microservice2.service.impl;

import com.bachelor.microservice2.client.GymOffersServiceCaller;
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
    private final GymOffersServiceCaller gymOffersServiceCaller;

    public UserServiceImpl(UserRepository userRepository, GymSubscriptionRepository gymSubscriptionRepository, OfferSubscriptionRepository offerSubscriptionRepository, GymOffersServiceCaller gymOffersServiceCaller) {
        this.userRepository = userRepository;
        this.gymSubscriptionRepository = gymSubscriptionRepository;
        this.offerSubscriptionRepository = offerSubscriptionRepository;
        this.gymOffersServiceCaller = gymOffersServiceCaller;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<GymDto> getAllGymsForUser(Long userId, String jwt) {
        List<Long> gymIds = getSubscribedGymIdsForUser(userId);
        return getGymDetails(gymIds, jwt);
    }

    @Override
    public List<OfferDto> getAllOffersForUser(Long userId, String jwt) {
        List<Long> offerIds = getSubscribedOfferIdsForUser(userId);
        return getOfferDetails(offerIds, jwt);
    }

    private List<Long> getSubscribedGymIdsForUser(Long userId) {
        userRepository.findById(userId).orElseThrow(UserDoesNotExist::new);
        return gymSubscriptionRepository.findAllByUserId(userId).stream()
                .map(GymSubscription::getGymId)
                .collect(Collectors.toList());
    }

    private List<GymDto> getGymDetails(List<Long> gymIds, String jwt) {
        return this.gymOffersServiceCaller.getGymsByIds(gymIds, jwt);
    }

    private List<Long> getSubscribedOfferIdsForUser(Long userId) {
        userRepository.findById(userId).orElseThrow(UserDoesNotExist::new);
        return offerSubscriptionRepository.findAllByUserIdAndIsOfferValidIsTrue(userId).stream()
                .map(OfferSubscription::getOfferId)
                .collect(Collectors.toList());
    }

    private List<OfferDto> getOfferDetails(List<Long> offerIds, String jwt) {
        return this.gymOffersServiceCaller.getOffersByIds(offerIds, jwt);
    }
}
