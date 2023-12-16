package com.example.Uber.controller;

import com.example.Uber.dto.Location;
import com.example.Uber.model.Trip;
import com.example.Uber.services.TripService;
import com.example.Uber.services.exceptions.InvalidTripStatusException;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TripController {

    private TripService tripService;


    public String acceptTrip(String cabId, String riderId){

        String tripId = tripService.acceptTrip(cabId, riderId);

        return tripId;
    }

    public void updateStatus(String tripId, String status) throws InvalidTripStatusException {

        tripService.updateStatus(tripId, status);

    }

    public Trip getTrip(String tripId) throws InvalidTripStatusException {

       return tripService.getTrip(tripId);

    }

}
