package com.hotelservice.hotelservice.service;

import com.hotelservice.hotelservice.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {

    public Hotel createHotel(Hotel hotel);

    public List<Hotel> findAllHotel();


    public Hotel getHotelById(Long id);



}
