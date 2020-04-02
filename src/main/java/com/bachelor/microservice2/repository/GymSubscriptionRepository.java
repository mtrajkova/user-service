package com.bachelor.microservice2.repository;

import com.bachelor.microservice2.model.GymSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GymSubscriptionRepository extends JpaRepository<GymSubscription, Long> {
    List<GymSubscription> findAllByGymId(Long gymId);
    List<GymSubscription> findAllByUserId(Long userId);
}
