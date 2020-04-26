package com.oocl.easyparkbackend.controller;

import com.oocl.easyparkbackend.model.ParkingLot;
import com.oocl.easyparkbackend.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-lots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService service;

    @GetMapping
    public List<ParkingLot> getParkingLots(@RequestParam(required = false) Double minHourRate,
                                           @RequestParam(required = false) Double maxHourRate,
                                           @RequestParam(required = false) Double maxDistance,
                                           @RequestParam(required = false) Boolean sortRatingFromHighToLow) {
        return service.findAll(minHourRate, maxHourRate, maxDistance, sortRatingFromHighToLow);
    }

    @GetMapping("/{parkingLotId}")
    public ParkingLot findParkingLotById(@PathVariable("parkingLotId") Integer parkingLotId) {
        return service.findParkingLotById(parkingLotId);
    }

    


}
