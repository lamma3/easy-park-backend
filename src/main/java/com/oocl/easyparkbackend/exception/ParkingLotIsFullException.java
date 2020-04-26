package com.oocl.easyparkbackend.exception;

public class ParkingLotIsFullException extends RuntimeException {

    private static final String MESSAGE = "Parking lot is full.";

    public ParkingLotIsFullException() {
        super(MESSAGE);
    }
}
