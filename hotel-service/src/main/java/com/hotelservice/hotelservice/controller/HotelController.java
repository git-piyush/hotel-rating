package com.hotelservice.hotelservice.controller;

import com.hotelservice.hotelservice.entities.Hotel;
import com.hotelservice.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel-service")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/hotel")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/hotel/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id){
        Hotel hotel1 = hotelService.getHotelById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/hotels")
    public ResponseEntity<List<Hotel>> getAllHotels(){
        List<Hotel> hotel1 = hotelService.findAllHotel();
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

}
