package com.parking.repository;

import com.parking.beans.ParkingDetails;
import com.parking.beans.ParkingSpace;
import com.parking.beans.Vehicle;
import com.parking.beans.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface ParkingLotRepository extends JpaRepository<Vehicle, Long> {
    @Query(value="select CURRENT_TIMESTAMP() - inTime from ParkingDetails where ticketId = :ticketId",
            nativeQuery = true)
    public Timestamp timeTakenByVehicle(long ticketId);

    @Query(value="select * from ParkingSpace ps where vehicleType = :vehicleType and vacant = 'true'",
            nativeQuery =  true)
    public ParkingSpace findVacantParkingSpace(VehicleType vehicleType);

    @Query(value="select * from ParkingSpace where parkingNumber = :parkingNumber",
            nativeQuery = true)
    public ParkingSpace vacantParkingSpace(long parkingNumber);

    @Query(value="select * from ParkingDetails where ticketId = :ticketId",
            nativeQuery = true)
    public ParkingDetails findParkingDetails(long ticketId);
}
