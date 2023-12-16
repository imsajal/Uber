package com.example.Uber;

import com.example.Uber.controller.CabController;
import com.example.Uber.controller.TripController;
import com.example.Uber.controller.UserController;
import com.example.Uber.dto.Location;
import com.example.Uber.model.Cab;
import com.example.Uber.services.CabService;
import com.example.Uber.services.TripService;
import com.example.Uber.services.UserService;
import com.example.Uber.services.exceptions.CabNotFoundException;
import com.example.Uber.services.exceptions.InvalidTripStatusException;
import com.example.Uber.services.impl.CabServiceImpl;
import com.example.Uber.services.impl.TripServiceImpl;
import com.example.Uber.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


class UberApplicationTests {

	private UserService userService;
	private CabService cabService;
	private TripService tripService;

	private CabController cabController;
	private TripController tripController;
	private UserController userController;

	@BeforeEach
	void setUp(){

		UserService userService = new UserServiceImpl();
		CabService cabService = new CabServiceImpl(userService);
		TripService tripService = new TripServiceImpl(userService, cabService);

		cabController = new CabController(cabService);
		tripController = new TripController(tripService);
		userController = new UserController(userService);

	}

	@Test
	void Test1() throws InvalidTripStatusException {

		// create 5 rider
		String  sidhi =
				userController.createUser("sidhi", "7000870372", "sidhi@gmail.com");
		String sajal =
				userController.createUser("sajal", "7000870377", "2050sajaljain@gmail.com");
		String mom =
				userController.createUser("mom", "7000870327", "mom@gmail.com");
		String ajay =
				userController.createUser("ajay", "7000870371", "ajay@gmail.com");
		String sourabh =
				userController.createUser("sourabh", "7000870311", "sourabh@gmail.com");

        // create 5 driver
		String driver1 =
				userController.createUser("driver1", "7000870221", "driver1@gmail.com");
		String driver2 =
				userController.createUser("driver2", "7000870111", "driver2@gmail.com");
		String driver3 =
				userController.createUser("driver3", "7000870111", "driver3@gmail.com");
		String driver4 =
				userController.createUser("driver4", "7000870111", "driver4@gmail.com");
		String driver5 =
				userController.createUser("driver5", "7000870111", "driver5@gmail.com");

		// create 5 cabs
		String cab1 =
					cabController.createCab(driver1 ,new Location(1.0,2.0), true);
		String cab2 =
				cabController.createCab(driver2 ,new Location(2.0,3.0), true);
		String cab3 =
				cabController.createCab(driver3, new Location(4.0,5.0), true);
		String cab4 =
				cabController.createCab(driver4 ,new Location(4.0,1.0), true);
		String cab5 =
				cabController.createCab(driver5 ,new Location(1.0,21.0), true);

		// create trip

		List<String> cabs = cabController.findCabs(new Location(9.0, 9.0));

		String tripToSajal = tripController.acceptTrip(cabs.get(0), sajal);

		tripController.updateStatus(tripToSajal, "Completed");

		Assertions.assertEquals(tripController.getTrip(tripToSajal).getTripStatus().toString(), "Completed");

		cabController.updateCabAvailability(cab1, false);
		cabController.updateCabAvailability(cab2, false);
		cabController.updateCabAvailability(cab3, false);
		cabController.updateCabAvailability(cab4, false);
		cabController.updateCabAvailability(cab5, false);

		Assertions.assertEquals(0,
				cabController.findCabs(new Location(1.0,2.0)).size());

		// update cab availability

		cabController.updateCabAvailability(cab1, true);

		Assertions.assertEquals(1, cabController.findCabs(new Location(1.0,2.0)).size());

		Assertions.assertEquals(cab1 , cabController.findCabs(new Location(1.0,2.0))
				.get(0));

		// assert only correct cabs are returned

		cabController.updateCabAvailability(cab1, true);
		cabController.updateCabAvailability(cab2, true);
		cabController.updateCabAvailability(cab3, true);
		cabController.updateCabAvailability(cab4, true);
		cabController.updateCabAvailability(cab5, true);

		Assertions.assertEquals(0, cabController.findCabs(new Location(100.0,101.0)).size());

	}

}
