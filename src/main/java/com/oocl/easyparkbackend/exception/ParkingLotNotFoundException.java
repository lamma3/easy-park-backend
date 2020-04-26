package com.oocl.easyparkbackend.exception;

public class ParkingLotNotFoundException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "Parking lot id %s not found.";

    public ParkingLotNotFoundException(Integer parkingLotId) {
        super(String.format(MESSAGE_FORMAT, parkingLotId));
    }
}
