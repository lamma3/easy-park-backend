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

    public static final int NO_AVAILABLE_POSITION = 0;
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(Integer parkingLotId) {
        ParkingLot targetedParkingLot = parkingLotRepository.findById(parkingLotId).orElseThrow(ParkingLotNotFoundException::new);

        if (targetedParkingLot.getAvailableCapacity().equals(NO_AVAILABLE_POSITION)) {
            throw new ParkingLotIsFullException();
        }

        Booking booking = new Booking();
        booking.setStatus("reserved");
        booking.setParkingLotId(parkingLotId);
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
            targetedBooking.setStatus(booking.getStatus());
        }

        if (booking.getParkingLotId() != null) {
            ParkingLot targetedParkingLot = parkingLotRepository.findById(booking.getParkingLotId()).orElseThrow(ParkingLotNotFoundException::new);

            if (targetedParkingLot.getAvailableCapacity().equals(NO_AVAILABLE_POSITION)) {
                throw new ParkingLotIsFullException();
            }
            targetedBooking.setParkingLotId(booking.getParkingLotId());
            targetedBooking.setParkingLot(targetedParkingLot);
        }
        return bookingRepository.save(targetedBooking);
    }
}

