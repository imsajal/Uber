package com.example.Uber.services;

import com.example.Uber.dto.Location;
import com.example.Uber.model.Cab;

import java.util.List;

public interface CabService {

    String createCab(String driverId, Location cabLocation, boolean availability);

    List<String> findCabs(Location riderLocation);

    void updateCabLocation(String cabId, Double x, Double y);

    void updateCabAvailability(String cabId, boolean availability);

    Cab getCab(String id);
}
