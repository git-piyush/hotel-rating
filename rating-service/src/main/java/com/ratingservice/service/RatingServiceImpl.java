package com.ratingservice.service;

import com.ratingservice.entities.Rating;
import com.ratingservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllRatingByUserId(Long userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getAllRatingByHotelId(Long hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public Rating findRatingByUserIdAndHotelId(Long userId, Long hotelId) {
        Rating rating = ratingRepository.findRatingByUserIdAndHotelId(userId, hotelId);
        if(rating!=null){
            return rating;
        }
        return null;
    }
}
