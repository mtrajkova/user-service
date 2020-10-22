package com.bachelor.microservice2.api;

import com.bachelor.microservice2.model.User;
import com.bachelor.microservice2.model.dto.GymDto;
import com.bachelor.microservice2.model.dto.OfferDto;
import com.bachelor.microservice2.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}/gyms")
    public List<GymDto> getAllGymsForUser(@PathVariable("username") String username, @RequestHeader("Authorization") String jwt) {
        return this.userService.getAllGymsForUser(username, jwt);
    }

    @GetMapping("/{username}/offers")
    public List<OfferDto> getAllOffersForUser(@PathVariable("username") String username, @RequestHeader("Authorization") String jwt) {
        return this.userService.getAllOffersForUser(username, jwt);
    }

    @PostMapping("/save-user")
    public void saveUser(@RequestBody User user){
        this.userService.saveNewUser(user);
    }

}
