package com.userservice.userservice.service;

import com.userservice.userservice.entities.Hotel;
import com.userservice.userservice.entities.Rating;
import com.userservice.userservice.entities.User;
import com.userservice.userservice.exception.ResourceNotFoundException;
import com.userservice.userservice.external.service.HotelService;
import com.userservice.userservice.external.service.RatingService;
import com.userservice.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> userlList1 = new ArrayList<>();
        List<User> userlList = userRepository.findAll();
        userlList.stream().forEach(user->{
            String url = "http://RATING-SERVICE/api/rating-service/rating/user/"+user.getUserId();
            Rating[] ratingArray = restTemplate.getForObject(url, Rating[].class);
            List<Rating> ratingList = Arrays.stream(ratingArray).collect(Collectors.toList());
            ratingList = ratingList.stream().map(rating->{
                String url1 = "http://HOTEL-SERVICE/api/hotel-service/hotel/"+rating.getHotelId();
                Hotel hotel = restTemplate.getForEntity(url1, Hotel.class).getBody();
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());
            user.setRatings(ratingList);
            userlList1.add(user);
        });
        return userlList1;
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException());
        System.out.println("before rating call!!");
        //call the rating ms to get rating given by user
        System.out.println(restTemplate);
        String url = "http://RATING-SERVICE/api/rating-service/rating/user/"+user.getUserId();
        Rating[] ratingArray = restTemplate.getForObject(url, Rating[].class);
        System.out.println("after rating call!!");
        List<Rating> ratingList = Arrays.stream(ratingArray).collect(Collectors.toList());

        logger.info("{}",ratingList);

        List<Rating> ratingList1 = ratingList.stream().map(rating->{
           // String url1 = "http://HOTEL-SERVICE/api/hotel-service/hotel/"+rating.getHotelId();
           // Hotel hotel = restTemplate.getForEntity(url1, Hotel.class).getBody();
            System.out.println("before hotel call!!");
           Hotel hotel = hotelService.getHotel(rating.getHotelId());
            System.out.println("after hotel call!!");

            logger.info("{}",hotel);
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        if(user!=null){
            user.setRatings(ratingList1);
            return user;
        }
        return null;
    }

    @Override
    public Void deleteUserById(Long id) {
        userRepository.deleteById(id);
        return null;
    }

    @Override
    public User updateUser(User user, Long id) {
        return null;
    }

    @Override
    public Rating createRatingForHotel(Rating rating) {
        Rating rating1 = ratingService.createRating(rating);
        return rating1;
    }
}
