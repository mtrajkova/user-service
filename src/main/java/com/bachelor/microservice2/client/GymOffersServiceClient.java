package com.bachelor.microservice2.client;

import com.bachelor.microservice2.model.dto.GymDto;
import com.bachelor.microservice2.model.dto.OfferDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Employee-Service")
public interface GymOffersServiceClient {

    @GetMapping("/gyms/{id}")
    GymDto getGymById(@PathVariable("id") Long gymId);

    @GetMapping("/offers/{id}")
    OfferDto getOfferById(@PathVariable("id") Long offerId);
}
