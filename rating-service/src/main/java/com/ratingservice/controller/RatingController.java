package com.ratingservice.controller;

import com.ratingservice.entities.Rating;
import com.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating-service")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/rating")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating rating1 = ratingService.findRatingByUserIdAndHotelId(rating.getUserId(), rating.getHotelId());
        Rating rating2 = null;
        if(rating1!=null){
            rating1.setRating(rating.getRating());
            rating1.setFeedback(rating.getFeedback());
            rating2 = ratingService.saveRating(rating1);
        }else{
            rating2 = ratingService.saveRating(rating);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(rating2);
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/rating")
    public ResponseEntity<List<Rating>> getAllRating(){
        return ResponseEntity.status(HttpStatus.CREATED).body( ratingService.getAllRating());
    }


    @GetMapping("/rating/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.CREATED).body( ratingService.getAllRatingByUserId(userId));
    }

    @GetMapping("/rating/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable Long hotelId){
        return ResponseEntity.status(HttpStatus.CREATED).body( ratingService.getAllRatingByHotelId(hotelId));
    }

}
