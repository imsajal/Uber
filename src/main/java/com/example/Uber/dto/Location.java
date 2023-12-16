package com.example.Uber.dto;

import com.example.Uber.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Location {

    @Setter
    private Double xCoordinate;
    @Setter
    private Double yCoordinate;
}
