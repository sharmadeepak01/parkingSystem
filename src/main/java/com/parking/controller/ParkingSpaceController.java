package com.parking.controller;

import com.parking.beans.ParkingDetails;
import com.parking.beans.ParkingSpace;
import com.parking.repository.ParkingLotRepository;
import com.parking.repository.ParkingSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;

@RestController
@RequestMapping("/api")
public class ParkingSpaceController {

    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private ParkingSpaceRepository parkingSpaceRepository;

    @GetMapping("parkingSpace/{ticketId}")
    public BigDecimal calculateFare(@PathVariable(value="ticketId") long ticketId) {
        Timestamp endTime = parkingLotRepository.timeTakenByVehicle(ticketId);
        ParkingDetails parkingDetails = parkingLotRepository.findParkingDetails(ticketId);
        parkingDetails.setOutTime(endTime);

        int totalInTime = getVehicleInTime(endTime, parkingDetails);

        parkingDetails.setFare(parkingDetails.getVehicle().getBaseFare().multiply(new BigDecimal(totalInTime)));
        parkingSpaceRepository.save(parkingDetails);
        ParkingSpace parkingSpace = parkingDetails.getParkingSpace();

        freeParkingSpace(parkingSpace.getParkingNumber(), parkingSpace.getFloor());

        return parkingDetails.getFare();
    }

    private void freeParkingSpace(int parkingNumber, int floor) {
        parkingSpaceRepository.vacantParkingSpace(parkingNumber, floor);
    }

    private int getVehicleInTime(Timestamp endTime, ParkingDetails parkingDetails) {
        Calendar calStartTime = Calendar.getInstance();
        calStartTime.setTime(parkingDetails.getInTime());
        int startHour = calStartTime.get(Calendar.HOUR_OF_DAY);

        Calendar calEndTime = Calendar.getInstance();
        calEndTime.setTime(endTime);
        int endHour = calEndTime.get(Calendar.HOUR_OF_DAY);
        return endHour - startHour;
    }
}
