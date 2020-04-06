package com.bachelor.microservice2.repository;

import com.bachelor.microservice2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
}
