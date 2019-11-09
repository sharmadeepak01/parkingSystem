package com.parking.repository;

import com.parking.beans.ParkingDetails;
import com.parking.beans.ParkingSpace;
import com.parking.beans.Vehicle;
import com.parking.beans.VehicleType;
import com.parking.exception.NoEmptySpaceAvailable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingDetails, Long> {

    @Query(value="update ParkingSpace set vacant = true where parkingNumber = :parkingNumber" +
            " and floor = :floor",
            nativeQuery = true)
    public int vacantParkingSpace(int parkingNumber, int floor);

    @Query(value="update ParkingSpace set vacant = false where parkingNumber = :parkingNumber " +
            "and floor = :floor",
            nativeQuery = true)
    public int occupyParkingSpace(int parkingNumber, int floor);
}