package com.parking.exception;

public class InvalidParkingNumberException extends Exception {
    public InvalidParkingNumberException() {}

    public InvalidParkingNumberException(String message) {
        super(message);
    }

    public InvalidParkingNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}