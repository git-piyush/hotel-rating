package com.userservice.userservice.service;

import com.userservice.userservice.entities.Rating;
import com.userservice.userservice.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    //user operation

    User saveUser(User user);

    List<User> getAllUser();

    User getUserById(Long id);

    Void deleteUserById(Long id);

    User updateUser(User user, Long id);

    Rating createRatingForHotel(Rating rating);
}
