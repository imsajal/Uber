package com.example.Uber.services;

import com.example.Uber.dto.Location;
import com.example.Uber.model.Cab;
import com.example.Uber.model.Trip;
import com.example.Uber.services.exceptions.InvalidTripStatusException;

import java.util.List;

public interface TripService {
    String acceptTrip(String cabId, String riderId);
    void updateStatus(String tripId, String status) throws InvalidTripStatusException;

    Trip getTrip(String id);
}
