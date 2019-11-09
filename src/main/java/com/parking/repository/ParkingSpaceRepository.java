package com.parking.repository;

import com.parking.beans.ParkingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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