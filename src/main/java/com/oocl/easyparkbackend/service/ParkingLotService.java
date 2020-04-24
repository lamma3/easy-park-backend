package com.oocl.easyparkbackend.service;

import com.oocl.easyparkbackend.model.ParkingLot;
import com.oocl.easyparkbackend.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository repository;

    public List<ParkingLot> findAll() {
        return repository.findAll();
    }
}
