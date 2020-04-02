package com.bachelor.microservice2.repository;

import com.bachelor.microservice2.model.OfferSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferSubscriptionRepository extends JpaRepository<OfferSubscription, Long> {
    List<OfferSubscription> findAllByUserIdAndOfferValidIsTrue(Long userId);
}
