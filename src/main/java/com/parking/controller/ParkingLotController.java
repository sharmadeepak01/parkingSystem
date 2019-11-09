package com.parking.controller;

import com.parking.beans.ParkingDetails;
import com.parking.beans.ParkingSpace;
import com.parking.beans.Vehicle;
import com.parking.beans.VehicleType;
import com.parking.exception.InvalidParkingNumberException;
import com.parking.exception.NoEmptySpaceAvailableException;
import com.parking.repository.ParkingLotRepository;
import com.parking.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ParkingLotController {

    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return parkingLotRepository.findAll();
    }

    @PostMapping("/parking/{vehicle}")
    public Vehicle addParkingDetails(@Valid @RequestBody Vehicle vehicle) throws NoEmptySpaceAvailableException {
        ParkingDetails parkingDetails = new ParkingDetails();
        parkingDetails.setInTime(new Timestamp(new Date().getTime()));
        parkingDetails.setVehicle(vehicle);
        ParkingSpace parkingSpace = getVacantParkingSpace(vehicle.getVehicleType());

        updateParkingSpace(parkingSpace.getParkingNumber(), parkingSpace.getFloor());
        parkingDetails.setParkingSpace(parkingSpace);
        return parkingLotRepository.save(vehicle);
    }

    private void updateParkingSpace(int parkingNumber, int floor) {
        parkingSpaceRepository.occupyParkingSpace(parkingNumber, floor);
    }

    private ParkingSpace getVacantParkingSpace(VehicleType vehicleType) throws NoEmptySpaceAvailableException {
        Optional<ParkingSpace> parkingSpaceOptional = Optional.of(parkingLotRepository.findVacantParkingSpace(vehicleType));
        if (parkingSpaceOptional.isPresent()) {
           return parkingSpaceOptional.get();
        } else {
            throw new NoEmptySpaceAvailableException("ParkingSpace is full for " + vehicleType);
        }
    }

    @GetMapping("/parking/{parkingSpaceNo}")
    public Vehicle getVehicle(@PathVariable(value = "parkingSpaceNo") Long parkingSpaceNo) throws InvalidParkingNumberException {
        return parkingLotRepository.findById(parkingSpaceNo)
                .orElseThrow(() -> new InvalidParkingNumberException("Parking Space Number is invalid"));
    }

}
