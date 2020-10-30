package com.bachelor.microservice2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GymSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long gymId;
    private Long userId;

    public GymSubscription() {
    }

    public GymSubscription(Long userId, Long gymId) {
        this.userId = userId;
        this.gymId = gymId;
    }

    public Long getId() {
        return id;
    }

    public Long getGymId() {
        return gymId;
    }

    public Long getUserId() {
        return userId;
    }
}
