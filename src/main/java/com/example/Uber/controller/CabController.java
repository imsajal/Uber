package com.example.Uber.controller;

import com.example.Uber.dto.Location;
import com.example.Uber.model.User;
import com.example.Uber.services.CabService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CabController {

    private CabService cabService;

    public List<String> findCabs(Location riderLocation){

        return cabService.findCabs(riderLocation);

    }

    public String createCab(String driverId, Location cabLocation, boolean availability){

       String cabId = cabService.createCab(driverId, cabLocation, availability);
       return cabId;
    }

    public void updateCabLocation(String cabId, Double x, Double y){

        cabService.updateCabLocation(cabId, x, y);
    }

    public void updateCabAvailability(String cabId, boolean availability){

        cabService.updateCabAvailability(cabId, availability);
    }
}
