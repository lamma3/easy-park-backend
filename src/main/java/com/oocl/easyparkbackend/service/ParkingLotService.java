package com.oocl.easyparkbackend.service;

import com.oocl.easyparkbackend.model.ParkingLot;
import com.oocl.easyparkbackend.repository.ParkingLotRepository;
import com.oocl.easyparkbackend.repository.ParkingLotRepositoryCustomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Service
public class ParkingLotService {

    @Autowired
    private ParkingLotRepository repository;

    @Autowired
    private ParkingLotRepositoryCustomImpl customRepository;

    public List<ParkingLot> findAll(Double priceFrom, Double priceTo, Double maxDistance, String ratingOrder) {
        return customRepository.findAllParkingLotsByFilterCondition(priceFrom, priceTo, maxDistance, ratingOrder);
    }

    public ParkingLot findParkingLotById(Integer parkingLotId) {
        return repository.findById(parkingLotId).orElse(null);
    }

}
