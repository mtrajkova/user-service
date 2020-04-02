package com.bachelor.microservice2.model.dto;

import java.time.LocalDateTime;

public class OfferDto {
    private Long id;
    private LocalDateTime startOfOffer;
    private LocalDateTime endOfOffer;
    private String gymName;
    private Integer price;
}
