package com.oocl.easyparkbackend.controller;

import com.oocl.easyparkbackend.model.ParkingLot;
import com.oocl.easyparkbackend.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking-lots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService service;

    @GetMapping
    public List<ParkingLot> getParkingLots() {
        return service.findAll();
    }

}
