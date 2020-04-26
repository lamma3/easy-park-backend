package com.oocl.easyparkbackend.exception;

import com.oocl.easyparkbackend.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ParkingLotExceptionHandler {
    @ExceptionHandler(ParkingLotNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleParkingLotNotFound(ParkingLotNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
