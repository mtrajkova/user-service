package com.bachelor.microservice2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class OfferSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;
    private Long offerId;
    private LocalDateTime startOfOffer;
    private boolean isOfferValid;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getOfferId() {
        return offerId;
    }

    public LocalDateTime getStartOfOffer() {
        return startOfOffer;
    }

    public boolean isOfferValid() {
        return isOfferValid;
    }
}
