package com.bachelor.microservice2.api;

import com.bachelor.microservice2.model.dto.GymDto;
import com.bachelor.microservice2.model.dto.OfferDto;
import com.bachelor.microservice2.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/gyms")
    public List<GymDto> getAllGymsForUser(@PathVariable("id") Long userId) {
        return this.userService.getAllGymsForUser(userId);
    }

    @GetMapping("/{id}/offers")
    public List<OfferDto> getAllOffersForUser(@PathVariable("id") Long userId) {
        return this.userService.getAllOffersForUser(userId);
    }
}
