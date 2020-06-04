package com.bachelor.microservice2.client;

import com.bachelor.microservice2.model.dto.GymDto;
import com.bachelor.microservice2.model.dto.OfferDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "Employee-Service")
public interface GymOffersServiceClient {

    @GetMapping("/gyms/{id}")
    GymDto getGymById(@PathVariable("id") Long gymId, @RequestHeader("Authorization") String jwt);

    @GetMapping("/offers/{id}")
    OfferDto getOfferById(@PathVariable("id") Long offerId, @RequestHeader("Authorization") String jwt);
}
