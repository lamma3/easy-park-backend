package com.oocl.easyparkbackend.repository;

import com.oocl.easyparkbackend.model.ParkingLot;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ParkingLotRepository {

    private List<ParkingLot> parkingLotList;

    public ParkingLotRepository() {
        this.parkingLotList= new ArrayList<>();
        this.parkingLotList.add(new ParkingLot(1,"A",10,10));
        this.parkingLotList.add(new ParkingLot(2,"B",10,10));
        this.parkingLotList.add(new ParkingLot(3,"C",10,10));


    }

    public List<ParkingLot> findAll() {
        return parkingLotList;
    }

    public ParkingLot findById(Integer parkingLotId) {
        return this.parkingLotList.stream().filter((parkingLot -> parkingLot.getParkingLotId().equals(parkingLotId))).findFirst().get();
    }
}
