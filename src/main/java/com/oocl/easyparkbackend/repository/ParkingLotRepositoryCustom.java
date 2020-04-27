package com.oocl.easyparkbackend.repository;

import com.oocl.easyparkbackend.model.ParkingLot;

import java.util.List;

interface ParkingLotRepositoryCustom{
    public List<ParkingLot> findAllParkingLotsByFilterCondition(Double priceFrom, Double priceTo, Double maxDistance, String ratingOrder);
}
