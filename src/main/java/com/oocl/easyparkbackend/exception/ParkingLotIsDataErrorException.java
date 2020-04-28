package com.oocl.easyparkbackend.exception;

public class ParkingLotIsDataErrorException extends RuntimeException {

    private static final String MESSAGE = "Please enter correct data.";

    public ParkingLotIsDataErrorException() {
        super(MESSAGE);
    }
}
