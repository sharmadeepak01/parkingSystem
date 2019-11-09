package com.parking.beans;

import java.math.BigDecimal;

public class Bus extends Vehicle {
    @Override
    public VehicleType getVehicleType() {
        return VehicleType.BUS;
    }

    @Override
    public BigDecimal getBaseFare() {
        return new BigDecimal("60");
    }
}
