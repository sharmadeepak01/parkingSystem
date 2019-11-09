package com.parking.exception;

public class NoEmptySpaceAvailable extends Exception {
    public NoEmptySpaceAvailable() {}

    public NoEmptySpaceAvailable(String message) {
        super(message);
    }

    public NoEmptySpaceAvailable(String message, Throwable cause) {
        super(message, cause);
    }
}
