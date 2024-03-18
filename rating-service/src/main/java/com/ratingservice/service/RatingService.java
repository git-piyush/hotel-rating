package com.ratingservice.service;

import com.ratingservice.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    Rating saveRating(Rating rating);

    List<Rating> getAllRating();

    List<Rating> getAllRatingByUserId(Long userId);

    List<Rating> getAllRatingByHotelId(Long hotelId);

    Rating findRatingByUserIdAndHotelId(Long userId, Long hotelId);

}
