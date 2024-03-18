package com.userservice.userservice.controller;

import com.userservice.userservice.entities.Rating;
import com.userservice.userservice.entities.User;
import com.userservice.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-service")
public class UserController {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    int retry = 1;
    //create
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //find by id
    @GetMapping("/user/{id}")
    //@CircuitBreaker(name="userRatingHotelBreaker", fallbackMethod = "userRatingHotelFallback")
    //@Retry(name="ratingAndHoteLServiceRetry", fallbackMethod = "userRatingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "userRatingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        logger.info("Retry count {} "+retry++);
        User user1 = userService.getUserById(id);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

    //creating fallback method for circuit breaker
    public ResponseEntity<User> userRatingHotelFallback(Long id, Exception ex) {
        logger.info("Fallback is executed because service is down : ", ex.getMessage());

        ex.printStackTrace();

        User user = User.builder().email("dummy@gmail.com").name("Dummy").about("This user is created dummy because some service is down").userId(141234L).build();
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }

    //find all user
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> user1 = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(user1);
    }

    @PostMapping("/users/rating")
    public ResponseEntity<Rating> createRatingForHotel(@RequestBody Rating rating){
        Rating rating1 = userService.createRatingForHotel(rating);
        return ResponseEntity.status(HttpStatus.OK).body(rating1);
    }

}
