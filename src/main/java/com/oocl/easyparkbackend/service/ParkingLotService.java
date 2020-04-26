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

    private static String ASC_ORDER = "asc";
    private static String DESC_ORDER = "desc";
    private static String HOUR_RATE = "hourRate";
    private static String DISTANCE = "distance";
    private static String RATING = "rating";


    @Autowired
    private ParkingLotRepository repository;

    @Resource
    private EntityManager entityManager;

    public ParkingLotService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<ParkingLot> findAll(Double priceFrom, Double priceTo, Double maxDistance, String ratingOrder) {

        List<Predicate> predicatesList = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ParkingLot> query = criteriaBuilder.createQuery(ParkingLot.class);
        Root<ParkingLot> parkingLotRoot = query.from(ParkingLot.class);

        if(priceFrom != null){
            Predicate minHourRatePredicate = criteriaBuilder.ge(parkingLotRoot.get(HOUR_RATE), priceFrom);
            predicatesList.add(minHourRatePredicate);
        }
        if(priceTo != null){
            Predicate maxHourRatePredicate = criteriaBuilder.le(parkingLotRoot.get(HOUR_RATE), priceTo);
            predicatesList.add(maxHourRatePredicate);
        }
        if(maxDistance != null){
            Predicate maxDistancePredicate = criteriaBuilder.le(parkingLotRoot.get(DISTANCE), maxDistance);
            predicatesList.add(maxDistancePredicate);
        }

        query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));

        if(ASC_ORDER.equals(ratingOrder)){
            query.orderBy(criteriaBuilder.asc(parkingLotRoot.get(RATING)));
        }else {
            query.orderBy(criteriaBuilder.desc(parkingLotRoot.get(RATING)));
        }

        List<ParkingLot> resultList = entityManager.createQuery(query).getResultList();

        return resultList;

    }

    public ParkingLot findParkingLotById(Integer parkingLotId) {
        return repository.findById(parkingLotId).orElse(null);
    }

}
