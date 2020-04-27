package com.oocl.easyparkbackend.repository;

import com.oocl.easyparkbackend.model.ParkingLot;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ParkingLotRepositoryCustomImpl implements ParkingLotRepositoryCustom {

    private static String ASC_ORDER = "asc";
    private static String DESC_ORDER = "desc";
    private static String HOUR_RATE = "hourRate";
    private static String DISTANCE = "distance";
    private static String RATING = "rating";


    @Resource
    private EntityManager entityManager;

    public ParkingLotRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<ParkingLot> findAllParkingLotsByFilterCondition(Double priceFrom, Double priceTo, Double maxDistance, String ratingOrder) {

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
}
