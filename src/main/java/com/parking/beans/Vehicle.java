package com.parking.beans;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "vehicle")
public abstract class Vehicle {
    @Id
    @NotNull
    private String registrationNumber;
    private VehicleType vehicleType;
    private String color;

    public abstract VehicleType getVehicleType();
    public abstract BigDecimal getBaseFare();

}
