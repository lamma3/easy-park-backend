package com.oocl.easyparkbackend.service;

import com.oocl.easyparkbackend.exception.ParkingLotNotFoundException;
import com.oocl.easyparkbackend.model.DistanceCalculator;
import com.oocl.easyparkbackend.model.ParkingLot;
import com.oocl.easyparkbackend.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Comparator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ParkingLotService {

    private static String ASC_ORDER = "asc";
    private DistanceCalculator distanceCalculator;
    @Autowired
    private ParkingLotRepository repository;

    public ParkingLotService(ParkingLotRepository repository) {
        this.repository = repository;
        distanceCalculator = new DistanceCalculator();
    }

    public List<ParkingLot> findAll(Double priceFrom, Double priceTo, Double maxDistance, String ratingOrder,
                                    Boolean needCharge, Double deviceLatitude, Double deviceLongitude) {
        List<ParkingLot> parkingLotList = repository.findAll();

        Stream<ParkingLot> parkingLotStream = parkingLotList.stream();
        if (priceFrom != null){
            parkingLotStream = parkingLotStream.filter(parkingLot -> priceFrom <= parkingLot.getHourRate());
        }

        if (priceTo != null){
            parkingLotStream = parkingLotStream.filter(parkingLot -> priceTo >= parkingLot.getHourRate());
        }

        if(maxDistance != null){
            parkingLotStream = parkingLotStream.filter(parkingLot -> maxDistance >= distanceCalculator.distance(deviceLatitude,deviceLongitude,parkingLot.getLatitude(),parkingLot.getLongitude()));
        }

        if(Boolean.TRUE.equals(needCharge)){
            parkingLotStream = parkingLotStream.filter(parkingLot -> parkingLot.getAvailableChargeCapacity() > 0);
        }

        List<ParkingLot> resultList = parkingLotStream.collect(Collectors.toList());

        if(ASC_ORDER.equals(ratingOrder)){
            resultList.sort(Comparator.comparingDouble(ParkingLot::getRating));
        }else {
            resultList.sort(Comparator.comparingDouble(ParkingLot::getRating).reversed());
        }

        return resultList;
    }

    public ParkingLot findParkingLotById(Integer parkingLotId) {
        return repository.findById(parkingLotId).orElse(null);
    }

    public ParkingLot updateParkingLot(Integer parkingLotId, ParkingLot parkingLot) {
        ParkingLot parkingLotToBeUpdated = repository.findById(parkingLotId).orElse(null);
        if(parkingLotToBeUpdated == null){
            throw new ParkingLotNotFoundException();
        }

        parkingLotToBeUpdated.update(parkingLot);

        return repository.save(parkingLotToBeUpdated);

    }
}
