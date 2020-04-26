package com.oocl.easyparkbackend.service;

import com.oocl.easyparkbackend.model.ParkingLot;
import com.oocl.easyparkbackend.repository.ParkingLotRepository;
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

    @Resource
    private EntityManager entityManager;

    public List<ParkingLot> findAll(Double minHourRate, Double maxHourRate, Double maxDistance, Boolean sortRatingFromHighToLow) {

        List<Predicate> predicatesList = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ParkingLot> query = criteriaBuilder.createQuery(ParkingLot.class);
        Root<ParkingLot> parkingLotRoot = query.from(ParkingLot.class);

        if(minHourRate != null){
            Predicate minHourRatePredicate = criteriaBuilder.ge(parkingLotRoot.get("hourRate"), minHourRate);
            predicatesList.add(minHourRatePredicate);
        }
        if(maxHourRate != null){
            Predicate maxHourRatePredicate = criteriaBuilder.le(parkingLotRoot.get("hourRate"), maxHourRate);
            predicatesList.add(maxHourRatePredicate);
        }
        if(maxDistance != null){
            Predicate maxDistancePredicate = criteriaBuilder.le(parkingLotRoot.get("distance"), maxDistance);
            predicatesList.add(maxDistancePredicate);
        }

        query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
        if(Boolean.FALSE.equals(sortRatingFromHighToLow)){
            query.orderBy(criteriaBuilder.asc(parkingLotRoot.get("rating")));
        }else {
            query.orderBy(criteriaBuilder.desc(parkingLotRoot.get("rating")));
        }

        List<ParkingLot> resultList = entityManager.createQuery(query).getResultList();

        return resultList;

    }

    public ParkingLot findParkingLotById(Integer parkingLotId) {
        return repository.findById(parkingLotId).orElse(null);
    }

}
