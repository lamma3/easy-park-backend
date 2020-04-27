package com.oocl.easyparkbackend.service;

import com.oocl.easyparkbackend.exception.BookingNotFoundException;
import com.oocl.easyparkbackend.exception.ParkingLotIsFullException;
import com.oocl.easyparkbackend.exception.ParkingLotNotFoundException;
import com.oocl.easyparkbackend.model.ParkingLot;
import com.oocl.easyparkbackend.model.Booking;
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

    public Booking createBooking(Integer parkingLotId) {
        ParkingLot existingParkingLot = parkingLotRepository.findById(parkingLotId).orElseThrow(ParkingLotNotFoundException::new);

        if (existingParkingLot.getAvailableCapacity().equals(NO_AVAILABLE_POSITION)) {
            throw new ParkingLotIsFullException();
        }

        existingParkingLot.setAvailableCapacity(existingParkingLot.getAvailableCapacity() - 1);
        existingParkingLot.setReservedCapacity(existingParkingLot.getReservedCapacity() + 1);

        ParkingLot targetedParkingLot = parkingLotRepository.save(existingParkingLot);

        Booking booking = new Booking(null,"reserved",parkingLotId,null);
        Booking returnBookingRecord = bookingRepository.save(booking);
        returnBookingRecord.setParkingLot(targetedParkingLot);
        return returnBookingRecord;
    }

    public Booking updateBooking(Integer parkingLotId, Integer bookingId, Booking booking) {
        ParkingLot originalParkingLot = parkingLotRepository.findById(parkingLotId).orElseThrow(ParkingLotNotFoundException::new);
        Booking targetedBooking = bookingRepository.findById(bookingId).orElseThrow(BookingNotFoundException::new);

        if (!targetedBooking.getParkingLotId().equals(originalParkingLot.getId())) {
            throw new BookingNotFoundException();
        }

        if (booking.getStatus() != null && !targetedBooking.getStatus().equals(booking.getStatus())) {
            targetedBooking.setStatus(booking.getStatus());

            if (booking.getStatus().equals("completed")) {
                originalParkingLot.setReservedCapacity(originalParkingLot.getReservedCapacity() - 1);
                originalParkingLot.setAvailableCapacity(originalParkingLot.getAvailableCapacity() + 1);
            }
            ParkingLot updatedParkingLot = parkingLotRepository.save(originalParkingLot);
            targetedBooking.setParkingLot(updatedParkingLot);
        }
        return bookingRepository.save(targetedBooking);
    }
}

