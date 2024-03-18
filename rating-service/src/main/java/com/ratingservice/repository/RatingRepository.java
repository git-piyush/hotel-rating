package com.ratingservice.repository;

import com.ratingservice.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByUserId(Long userId);

    List<Rating> findByHotelId(Long hotelId);

    Rating findRatingByUserIdAndHotelId(Long userId, Long hotelId);

}
