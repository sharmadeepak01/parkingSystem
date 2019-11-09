package com.parking.beans;

import java.math.BigDecimal;

public class Car extends Vehicle {
    @Override
    public VehicleType getVehicleType() {
        return VehicleType.FOUR_WHEELER;
    }

    @Override
    public BigDecimal getBaseFare() {
        return new BigDecimal("35");
    }
}
