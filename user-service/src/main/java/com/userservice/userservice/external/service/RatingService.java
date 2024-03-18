package com.userservice.userservice.external.service;

import com.userservice.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {


    //GET

    //POST
    @PostMapping("/api/rating-service/rating")
    public Rating createRating(@RequestBody Rating rating);

    //DELETE

    //PUT
}
