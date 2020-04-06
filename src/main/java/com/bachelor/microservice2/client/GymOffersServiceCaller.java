package com.bachelor.microservice2.client;

import com.bachelor.microservice2.model.dto.GymDto;
import com.bachelor.microservice2.model.dto.OfferDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GymOffersServiceCaller {

    private final GymOffersServiceClient gymOffersServiceClient;

    public GymOffersServiceCaller(GymOffersServiceClient gymOffersServiceClient) {
        this.gymOffersServiceClient = gymOffersServiceClient;
    }

    public List<GymDto> getGymsByIds(List<Long> gymIds) {
        List<GymDto> gyms = new ArrayList<>();

        gymIds.forEach(gymId -> gyms.add(new GymDto(gymOffersServiceClient.getGymById(gymId))));
        return gyms;
    }

    public List<OfferDto> getOffersByIds(List<Long> offerIds) {
        List<OfferDto> offers = new ArrayList<>();

        offerIds.forEach(offerId -> offers.add(new OfferDto(gymOffersServiceClient.getOfferById(offerId))));
        return offers;
    }
}
