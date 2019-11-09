package com.parking.beans;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "ParkingSpace")
public class ParkingSpace {
    private int floor;
    private int parkingNumber;
    private boolean vacant;
    private Set<VehicleType> vehicleTypes;

}
