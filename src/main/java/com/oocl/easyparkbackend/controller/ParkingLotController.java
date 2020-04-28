package com.oocl.easyparkbackend.controller;

import com.oocl.easyparkbackend.model.Booking;
import com.oocl.easyparkbackend.model.ParkingLot;
import com.oocl.easyparkbackend.model.Rating;
import com.oocl.easyparkbackend.service.BookingService;
import com.oocl.easyparkbackend.service.ParkingLotService;
import com.oocl.easyparkbackend.service.RatingService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-lots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService service;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<ParkingLot> getParkingLots(@RequestParam(required = false) Double priceFrom,
                                           @RequestParam(required = false) Double priceTo,
                                           @RequestParam(required = false) Double distance,
                                           @RequestParam(required = false) Boolean needCharge,
                                           @RequestParam(required = false) String ratingOrder,
                                           @RequestParam(required = false) Double deviceLatitude,
                                           @RequestParam(required = false) Double deviceLongitude) {
        return service.findAll(priceFrom, priceTo, distance, ratingOrder, needCharge,deviceLatitude,deviceLongitude);
    }

    @GetMapping("/{parkingLotId}")
    public ParkingLot findParkingLotById(@PathVariable("parkingLotId") Integer parkingLotId) {
        return service.findParkingLotById(parkingLotId);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "{\"message\": \"Parking lot not found.\"}")
    })
    @PatchMapping("/{parkingLotId}")
    public ParkingLot updateParkingLot(@PathVariable Integer parkingLotId, @RequestBody ParkingLot parkingLot) {
        return service.updateParkingLot(parkingLotId, parkingLot);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "{\"message\": \"Parking lot not found.\"}")
    })
    @PostMapping("/{parkingLotId}/ratings")
    @ResponseStatus(HttpStatus.CREATED)
    public Rating createRating(@PathVariable Integer parkingLotId, @RequestBody Rating rating) {
        return ratingService.createRating(parkingLotId, rating);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "{\"message\": \"Parking lot is full.\"}"),
            @ApiResponse(code = 404, message = "{\"message\": \"Parking lot not found.\"}")
    })
    @PostMapping("/{parkingLotId}/bookings")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@PathVariable Integer parkingLotId, @RequestBody Booking booking) {
        return bookingService.createBooking(parkingLotId,booking);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "{\"message\": \"Parking lot not found.\"}"),
            @ApiResponse(code = 404, message = "{\"message\": \"Booking not found.\"}")
    })
    @PatchMapping("/{parkingLotId}/bookings/{bookingId}")
    public Booking updateBooking(@PathVariable Integer parkingLotId, @PathVariable Integer bookingId, @RequestBody Booking booking) {
        return bookingService.updateBooking(parkingLotId,bookingId, booking);
    }

    @GetMapping("/bookings/{bookingId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking getBookingById(@PathVariable Integer bookingId) {
        return bookingService.getBookingById(bookingId);
    }

}
