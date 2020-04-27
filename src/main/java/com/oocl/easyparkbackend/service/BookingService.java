package com.oocl.easyparkbackend.service;

import com.oocl.easyparkbackend.exception.BookingNotFoundException;
import com.oocl.easyparkbackend.exception.ParkingLotIsFullException;
import com.oocl.easyparkbackend.exception.ParkingLotNotFoundException;
import com.oocl.easyparkbackend.model.Booking;
import com.oocl.easyparkbackend.model.ParkingLot;
import com.oocl.easyparkbackend.repository.BookingRepository;
import com.oocl.easyparkbackend.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private static final int NO_AVAILABLE_POSITION = 0;
    private static final int INCREMENT = 1;
    private static final int DECREMENT = -1;

    private enum Status {
        RESERVED,
        COMPLETE
    }

    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private BookingRepository bookingRepository;

    private void bookingParkingLotPosition(ParkingLot targetedParkingLot) {
        targetedParkingLot.setAvailableCapacity(targetedParkingLot.getAvailableCapacity() + DECREMENT);
        targetedParkingLot.setReservedCapacity(targetedParkingLot.getReservedCapacity() + INCREMENT);
        parkingLotRepository.save(targetedParkingLot);
    }

    private void releaseParkingLotPosition(ParkingLot originalParkingLot) {
        originalParkingLot.setAvailableCapacity(originalParkingLot.getAvailableCapacity() + INCREMENT);
        originalParkingLot.setReservedCapacity(originalParkingLot.getReservedCapacity() + DECREMENT);
        parkingLotRepository.save(originalParkingLot);
    }

    public Booking createBooking(Integer parkingLotId) {
        ParkingLot targetedParkingLot = parkingLotRepository.findById(parkingLotId).orElseThrow(ParkingLotNotFoundException::new);

        if (targetedParkingLot.getAvailableCapacity().equals(NO_AVAILABLE_POSITION)) {
            throw new ParkingLotIsFullException();
        }

        bookingParkingLotPosition(targetedParkingLot);

        Booking booking = new Booking(null, "RESERVED", parkingLotId, null);
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

        if (booking.getStatus() != null) {
            Status status = Status.valueOf(booking.getStatus());
            Status originalStatus = Status.valueOf(targetedBooking.getStatus());
            if (status == Status.COMPLETE && originalStatus == Status.RESERVED) {
                targetedBooking.setStatus(booking.getStatus());
                releaseParkingLotPosition(originalParkingLot);
            }
        }
        return bookingRepository.save(targetedBooking);
    }
}