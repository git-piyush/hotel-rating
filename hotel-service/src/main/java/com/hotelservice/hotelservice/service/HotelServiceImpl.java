package com.hotelservice.hotelservice.service;

import com.hotelservice.hotelservice.entities.Hotel;
import com.hotelservice.hotelservice.exception.ResourceNotFoundException;
import com.hotelservice.hotelservice.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> findAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(Long id) {
       Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException());
        return hotel;
    }
}
