package com.oocl.easyparkbackend.service;

import com.oocl.easyparkbackend.exception.BookingNotFoundException;
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

    private static final int NO_AVAILABLE_POSITION = 0;
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public ParkingLotBooking createBooking(Integer parkingLotId) {
        ParkingLot existingParkingLot = parkingLotRepository.findById(parkingLotId).orElseThrow(ParkingLotNotFoundException::new);

        if (existingParkingLot.getAvailableCapacity().equals(NO_AVAILABLE_POSITION)) {
            throw new ParkingLotIsFullException();
        }

        existingParkingLot.setAvailableCapacity(existingParkingLot.getAvailableCapacity() - 1);
        existingParkingLot.setReservedCapacity(existingParkingLot.getReservedCapacity() + 1);

        ParkingLot targetedParkingLot = parkingLotRepository.save(existingParkingLot);

        ParkingLotBooking parkingLotBooking = new ParkingLotBooking(null,"reserved",parkingLotId,null);
        ParkingLotBooking returnParkingLotBookingRecord = bookingRepository.save(parkingLotBooking);
        returnParkingLotBookingRecord.setParkingLot(targetedParkingLot);
        return returnParkingLotBookingRecord;
    }

    public ParkingLotBooking updateBooking(Integer parkingLotId, Integer bookingId, ParkingLotBooking parkingLotBooking) {
        ParkingLot originalParkingLot = parkingLotRepository.findById(parkingLotId).orElseThrow(ParkingLotNotFoundException::new);
        ParkingLotBooking targetedParkingLotBooking = bookingRepository.findById(bookingId).orElseThrow(BookingNotFoundException::new);

        if (!targetedParkingLotBooking.getParkingLotId().equals(originalParkingLot.getId())) {
            throw new BookingNotFoundException();
        }

        if (parkingLotBooking.getStatus() != null && !targetedParkingLotBooking.getStatus().equals(parkingLotBooking.getStatus())) {
            targetedParkingLotBooking.setStatus(parkingLotBooking.getStatus());

            if (parkingLotBooking.getStatus().equals("completed")) {
                originalParkingLot.setReservedCapacity(originalParkingLot.getReservedCapacity() - 1);
                originalParkingLot.setAvailableCapacity(originalParkingLot.getAvailableCapacity() + 1);
            }
            ParkingLot updatedParkingLot = parkingLotRepository.save(originalParkingLot);
            targetedParkingLotBooking.setParkingLot(updatedParkingLot);
        }
        return bookingRepository.save(targetedParkingLotBooking);
    }
}

