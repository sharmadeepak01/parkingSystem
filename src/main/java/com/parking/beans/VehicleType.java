package com.parking.beans;

public enum VehicleType {
    TWO_WHEELER("Two Wheeler"),
    FOUR_WHEELER("Four Wheeler"),
    BUS("Bus");

    private String vehicleType;

    VehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
