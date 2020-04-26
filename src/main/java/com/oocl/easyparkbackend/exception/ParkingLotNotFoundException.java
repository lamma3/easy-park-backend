package com.oocl.easyparkbackend.exception;

public class ParkingLotNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Parking lot not found.";

    public ParkingLotNotFoundException() {
        super(MESSAGE);
    }
}
