package com.userservice.userservice.entities;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    private Long id;

    private Long userId;

    private Long hotelId;

    private int rating;

    private String feedback;

    @Transient
    private Hotel hotel= new Hotel();

}
