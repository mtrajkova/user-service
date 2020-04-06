package com.bachelor.microservice2.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class OfferDto {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endOfOffer;
    private Integer durationInDays;
    private String gymName;
    private Integer price;
    private GymDto gym;

    public OfferDto() {
    }

    public OfferDto(OfferDto offerDto) {
        this.gymName = offerDto.gymName;
        this.startDate = offerDto.startDate;
        this.endOfOffer = offerDto.endOfOffer;
        this.durationInDays = offerDto.durationInDays;
        this.price = offerDto.price;
        this.gymName = offerDto.getGym().getName();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndOfOffer() {
        return endOfOffer;
    }

    public String getGymName() {
        return gymName;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getDurationInDays() {
        return durationInDays;
    }

    public GymDto getGym() {
        return gym;
    }
}
