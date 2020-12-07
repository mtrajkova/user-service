package com.bachelor.microservice2.service.impl;

import com.bachelor.microservice2.client.GymOffersServiceCaller;
import com.bachelor.microservice2.client.payment.PaymentServiceCaller;
import com.bachelor.microservice2.exception.*;
import com.bachelor.microservice2.model.GymSubscription;
import com.bachelor.microservice2.model.OfferSubscription;
import com.bachelor.microservice2.model.User;
import com.bachelor.microservice2.model.dto.GymDto;
import com.bachelor.microservice2.model.dto.OfferDto;
import com.bachelor.microservice2.repository.GymSubscriptionRepository;
import com.bachelor.microservice2.repository.OfferSubscriptionRepository;
import com.bachelor.microservice2.repository.UserRepository;
import com.bachelor.microservice2.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GymSubscriptionRepository gymSubscriptionRepository;
    private final OfferSubscriptionRepository offerSubscriptionRepository;
    private final GymOffersServiceCaller gymOffersServiceCaller;
    private final PaymentServiceCaller paymentServiceCaller;

    public UserServiceImpl(UserRepository userRepository, GymSubscriptionRepository gymSubscriptionRepository, OfferSubscriptionRepository offerSubscriptionRepository, GymOffersServiceCaller gymOffersServiceCaller, PaymentServiceCaller paymentServiceCaller) {
        this.userRepository = userRepository;
        this.gymSubscriptionRepository = gymSubscriptionRepository;
        this.offerSubscriptionRepository = offerSubscriptionRepository;
        this.gymOffersServiceCaller = gymOffersServiceCaller;
        this.paymentServiceCaller = paymentServiceCaller;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<GymDto> getAllGymsForUser(String username, String jwt) {
        List<Long> gymIds = getSubscribedGymIdsForUser(username);
        return getGymDetails(gymIds, jwt);
    }

    @Override
    public List<OfferDto> getAllOffersForUser(String username, String jwt) {
        List<Long> offerIds = getSubscribedOfferIdsForUser(username);
        return getOfferDetails(offerIds, jwt);
    }

    @Override
    public List<OfferDto> getCurrentOffers(String username, String jwt) {
        List<OfferDto> currentOffers = getAllOffersForUser(username, jwt);
        currentOffers.forEach(offerDto ->
                offerDto.setEndOfOffer(calculateEndOfOfferSubscription(username, offerDto.getId())));
        return currentOffers;
    }

    @Override
    public void saveNewUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void checkIfUserIsAlreadySubscribedToOffer(String username, Long offerId) {
        User foundUser = userRepository.findByUsername(username).orElseThrow(UserDoesNotExist::new);
        List<OfferSubscription> offerSubscriptions = offerSubscriptionRepository.findAllByUserIdAndOfferId(foundUser.getId(), offerId);
        boolean hasValidCurrentOffer = offerSubscriptions.stream().anyMatch(OfferSubscription::isOfferValid);

        if (!offerSubscriptions.isEmpty() && hasValidCurrentOffer) {
            throw new UserIsAlreadySubscribedToOffer();
        }
    }

    @Override
    public void subscribeToGym(String username, Long gymId) {
        User foundUser = userRepository.findByUsername(username).orElseThrow(UserDoesNotExist::new);
        if (gymSubscriptionRepository.findAllByUserIdAndGymId(foundUser.getId(), gymId) != null) {
            throw new UserIsAlreadySubscribedToGym();
        }

        this.gymSubscriptionRepository.save(new GymSubscription(foundUser.getId(), gymId));
    }

    @Override
    public void unsubscribeToGym(String username, Long gymId) {
        User foundUser = userRepository.findByUsername(username).orElseThrow(UserDoesNotExist::new);
        GymSubscription gymSubscription = gymSubscriptionRepository.findAllByUserIdAndGymId(foundUser.getId(), gymId);
        if (gymSubscription != null) {
            this.gymSubscriptionRepository.delete(gymSubscription);
        }
    }

    @Override
    public void payForOffer(String username, Long offerId, String email, String token, String amount, String jwt) {
        this.checkIfUserIsAlreadySubscribedToOffer(username, offerId);

        User foundUser = userRepository.findByUsername(username).orElseThrow(UserDoesNotExist::new);
        String chargeId = this.paymentServiceCaller.chargeForOffer(token, amount, jwt, email);
        if (chargeId != null) {
            OfferDto offerDto = this.gymOffersServiceCaller.getOfferById(offerId, jwt);
            this.offerSubscriptionRepository.save(new OfferSubscription(foundUser.getId(), offerId, offerDto.getDurationInDays()));
        } else {
            throw new PaymentFailed();
        }
    }

    private List<Long> getSubscribedGymIdsForUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserDoesNotExist::new);
        return gymSubscriptionRepository.findAllByUserId(user.getId()).stream()
                .map(GymSubscription::getGymId)
                .collect(Collectors.toList());
    }

    private List<GymDto> getGymDetails(List<Long> gymIds, String jwt) {
        return this.gymOffersServiceCaller.getGymsByIds(gymIds, jwt);
    }

    private List<Long> getSubscribedOfferIdsForUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(UserDoesNotExist::new);
        return offerSubscriptionRepository.findAllByUserIdAndIsOfferValidIsTrue(user.getId()).stream()
                .map(OfferSubscription::getOfferId)
                .collect(Collectors.toList());
    }

    private List<OfferDto> getOfferDetails(List<Long> offerIds, String jwt) {
        return this.gymOffersServiceCaller.getOffersByIds(offerIds, jwt);
    }

    private LocalDateTime calculateEndOfOfferSubscription(String username, Long offerId) {
        User foundUser = userRepository.findByUsername(username).orElseThrow(UserDoesNotExist::new);
        return offerSubscriptionRepository.findAllByUserIdAndOfferId(foundUser.getId(), offerId).stream()
                .filter(OfferSubscription::isOfferSubscriptionValidOnCurrentDate)
                .map(OfferSubscription::getValidUntil)
                .findFirst().orElseThrow(UserNotSubscribedToOffer::new);
    }

    @Scheduled(cron = "59 23 * * ?")
    public void deleteExpiredOffers() {
        this.offerSubscriptionRepository.findAll()
                .stream()
                .filter(offerSubscription -> !offerSubscription.isOfferSubscriptionValidOnCurrentDate())
                .forEach(offerSubscription -> {
                    offerSubscription = offerSubscription.invalidateOfferSubscription();
                    this.offerSubscriptionRepository.save(offerSubscription);
                });
    }
}
