package com.example.cabbookingapplication.Service;

import com.example.cabbookingapplication.Model.Driver;
import com.example.cabbookingapplication.Model.Location;
import com.example.cabbookingapplication.Model.User;
import com.example.cabbookingapplication.Repository.DriverRepository;
import com.example.cabbookingapplication.Repository.UserRepository;
import java.util.ArrayList;
import java.util.List;

public class CabBookingService {
    private UserRepository userRepository;
    private DriverRepository driverRepository;

    public CabBookingService(UserRepository userRepository, DriverRepository driverRepository) {
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
    }

    public synchronized void addUser(String name, String gender, int age) {
        userRepository.addUser(new User(name, gender, age));
    }

    public synchronized void addDriver(String name, String gender, int age, String vehicleDetails, Location currentLocation) {
        driverRepository.addDriver(new Driver(name, gender, age, vehicleDetails, currentLocation));
    }

    public synchronized List<Driver> findRide(String username, Location source, Location destination) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + username);
        }

        List<Driver> availableRides = new ArrayList<>();
        for (Driver driver : driverRepository.getDrivers()) {
            if (driver.isAvailable() && isDriverInRange(driver, source, destination)) {
                availableRides.add(driver);
            }
        }
        return availableRides;
    }

    private synchronized boolean isDriverInRange(Driver driver, Location source, Location destination) {
        // ... (unchanged)
        return true;
    }
}
