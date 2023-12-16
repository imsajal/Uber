package com.example.Uber.model;

import com.example.Uber.dto.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Cab {

    private String id;
    @Setter
    private Location cabLocation;
    @Setter
    private User driver;
    @Setter
    private boolean isAvailable;

}
