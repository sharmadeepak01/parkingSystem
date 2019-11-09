package com.parking.beans;

import java.math.BigDecimal;

public class Bike extends Vehicle {
    @Override
    public VehicleType getVehicleType() {
        return VehicleType.TWO_WHEELER;
    }

    @Override
    public BigDecimal getBaseFare() {
        return new BigDecimal("20");
    }
}
