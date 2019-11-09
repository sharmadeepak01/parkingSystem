package com.parking.beans;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "parkingDetail")
public class ParkingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ticketId;
    private Vehicle vehicle;
    private ParkingSpace parkingSpace;
    private Timestamp inTime;
    private Timestamp outTime;
    private BigDecimal fare;


}
