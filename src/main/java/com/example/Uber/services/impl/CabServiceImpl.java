package com.example.Uber.services.impl;

import com.example.Uber.dto.Location;
import com.example.Uber.model.Cab;
import com.example.Uber.model.User;
import com.example.Uber.services.CabService;
import com.example.Uber.services.UserService;
import com.example.Uber.services.exceptions.CabNotFoundException;
import com.example.Uber.services.exceptions.UserNotFoundException;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.Uber.Constant.UberConstants.MAXIMUM_ALLOWED_DISTANCE_BETWEEN_CAB_AND_RIDER;

public class CabServiceImpl implements CabService {

    Map<String, Cab> cabs = new HashMap<>();

    private UserService userService;

    public CabServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String createCab(String driverId, Location cabLocation, boolean availability) {

        String id = UUID.randomUUID().toString();

        Cab cab = new Cab(id, cabLocation, userService.getUser(driverId), availability);
        cabs.put(id, cab);

        return id;
    }

    @Override
    public void updateCabLocation(String cabId, Double x, Double y) {

        if (!cabs.containsKey(cabId)) throw new CabNotFoundException();

        Cab cab = cabs.get(cabId);
        cab.getCabLocation().setXCoordinate(x);
        cab.getCabLocation().setYCoordinate(y);

    }

    @Override
    public void updateCabAvailability(String cabId, boolean availability) {

        if (!cabs.containsKey(cabId)) throw new CabNotFoundException();

        Cab cab = cabs.get(cabId);
        cab.setAvailable(availability);
    }

    @Override
    public Cab getCab(String id) {

        if (cabs.containsKey(id)) return cabs.get(id);
        else throw new CabNotFoundException();
    }

    @Override
    public List<String> findCabs(Location riderLocation) {

        return cabs.values().stream().filter(

                cab -> {
                    return cab.isAvailable() && findDistance(cab.getCabLocation(), riderLocation)
                            <= MAXIMUM_ALLOWED_DISTANCE_BETWEEN_CAB_AND_RIDER ;
                }).map(Cab::getId).collect(Collectors.toList());

    }





    private Double findDistance(Location cabLocation, Location riderLocation){
       return Math.sqrt(Math.pow(cabLocation.getXCoordinate() - riderLocation.getXCoordinate(), 2) +
                Math.pow(cabLocation.getYCoordinate() - riderLocation.getYCoordinate(), 2));
    }
}
