package com.parking.exception;

public class NoEmptySpaceAvailableException extends Exception {
    public NoEmptySpaceAvailableException() {}

    public NoEmptySpaceAvailableException(String message) {
        super(message);
    }

    public NoEmptySpaceAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
