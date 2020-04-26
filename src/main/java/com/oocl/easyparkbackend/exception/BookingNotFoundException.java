package com.oocl.easyparkbackend.exception;

public class BookingNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Booking lot not found.";

    public BookingNotFoundException() {
        super(MESSAGE);
    }
}
