package com.example.Uber.model;

import com.example.Uber.dto.Location;
import com.example.Uber.enums.TripStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Trip {

    private String id;
    @Setter
    private Cab cab;
    @Setter
    private TripStatus tripStatus;
    private User driver;
    private User rider;

}
