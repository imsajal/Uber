package com.example.Uber.services.impl;

import com.example.Uber.dto.Location;
import com.example.Uber.enums.TripStatus;
import com.example.Uber.model.Cab;
import com.example.Uber.model.Trip;
import com.example.Uber.model.User;
import com.example.Uber.services.CabService;
import com.example.Uber.services.TripService;
import com.example.Uber.services.UserService;
import com.example.Uber.services.exceptions.CabNotFoundException;
import com.example.Uber.services.exceptions.InvalidTripStatusException;
import com.example.Uber.services.exceptions.TripNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TripServiceImpl implements TripService {

    Map<String, Trip> trips = new HashMap<>();

    private UserService userService;
    private CabService cabService;

    public TripServiceImpl(UserService userService, CabService cabService){
        this.userService = userService;
        this.cabService = cabService;
    }

    @Override
    public String acceptTrip(String cabId, String riderId) {

        String id = UUID.randomUUID().toString();

        Cab cab = cabService.getCab(cabId);
        User rider = userService.getUser(riderId);

        Trip trip = new Trip(id,cab, TripStatus.Active, cab.getDriver(), rider);
        trips.put(id, trip);

        return id;
    }

    @Override
    public void updateStatus(String tripId, String status) throws InvalidTripStatusException {

        if(!trips.containsKey(tripId)) throw new TripNotFoundException();

        TripStatus newTripStatus = TripStatus.valueOf(status);
        Trip trip = trips.get(tripId);
        if(TripStatus.Active.equals(trip.getTripStatus()) && TripStatus.Completed.equals(newTripStatus))
        trips.get(tripId).setTripStatus(newTripStatus);
        else throw new InvalidTripStatusException();

    }

    @Override
    public Trip getTrip(String id) {

        if (trips.containsKey(id)) return trips.get(id);
        else throw new TripNotFoundException();
    }

}
