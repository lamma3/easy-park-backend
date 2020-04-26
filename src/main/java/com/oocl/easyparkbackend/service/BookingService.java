package com.oocl.easyparkbackend.service;

import com.oocl.easyparkbackend.exception.ParkingLotIsFullException;
import com.oocl.easyparkbackend.exception.ParkingLotNotFoundException;
import com.oocl.easyparkbackend.model.ParkingLot;
import com.oocl.easyparkbackend.model.ParkingLotBooking;
import com.oocl.easyparkbackend.repository.BookingRepository;
import com.oocl.easyparkbackend.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    public static final int NO_AVAILABLE_POSITION = 0;
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public ParkingLotBooking createBooking(Integer parkingLotId) {
        ParkingLot targetedParkingLot = parkingLotRepository.findById(parkingLotId).orElseThrow(ParkingLotNotFoundException::new);

        if (targetedParkingLot.getAvailableCapacity().equals(NO_AVAILABLE_POSITION)) {
            throw new ParkingLotIsFullException();
        }

        ParkingLotBooking parkingLotBooking = new ParkingLotBooking();
        parkingLotBooking.setStatus("reserved");
        parkingLotBooking.setParkingLotId(parkingLotId);
       ParkingLotBooking returnBookingRecord = bookingRepository.save(parkingLotBooking);
       returnBookingRecord.setParkingLot(targetedParkingLot);
        return returnBookingRecord;
    }
}
