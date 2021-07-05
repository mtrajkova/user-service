package com.bachelor.microservice2.api;

import com.bachelor.microservice2.model.HttpResponse;
import com.bachelor.microservice2.model.User;
import com.bachelor.microservice2.model.dto.GymDto;
import com.bachelor.microservice2.model.dto.OfferDto;
import com.bachelor.microservice2.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{username}/current-offers")
    public List<OfferDto> getCurrentOffers(@PathVariable("username") String username, @RequestHeader("Authorization") String jwt) {
        return this.userService.getCurrentOffers(username, jwt);
    }

    @PostMapping("/{username}/offers/{offerId}")
    public ResponseEntity<Void> subscribeToOffer(@PathVariable("username") String username, @PathVariable("offerId") Long offerId, @RequestHeader("Authorization") String jwt) {
        this.userService.checkIfUserIsAlreadySubscribedToOffer(username, offerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{username}/offers/{offerId}/pay")
    public ResponseEntity<HttpResponse> payForOffer(@PathVariable("username") String username, @PathVariable("offerId") Long offerId,
                                                    @RequestBody String email, @RequestHeader("Authorization") String jwt,
                                                    @RequestHeader("token") String token, @RequestHeader("amount") String amount) {
        this.userService.payForOffer(username, offerId, email, token, amount, jwt);
        return ResponseEntity.ok(new HttpResponse(null, "Offer payed successfully", HttpStatus.OK.value()));
    }

    @PostMapping("/{username}/gyms/{gymId}")
    public ResponseEntity<Void> subscribeToGym(@PathVariable("username") String username, @PathVariable("gymId") Long gymId, @RequestHeader("Authorization") String jwt) {
        this.userService.subscribeToGym(username, gymId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{username}/gyms/{gymId}")
    public ResponseEntity<Void> unsubscribeFromGym(@PathVariable("username") String username, @PathVariable("gymId") Long gymId, @RequestHeader("Authorization") String jwt) {
        this.userService.unsubscribeToGym(username, gymId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save-user")
    public void saveUser(@RequestBody User user) {
        this.userService.saveNewUser(user);
    }

}
