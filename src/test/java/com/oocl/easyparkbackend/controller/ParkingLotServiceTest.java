package com.oocl.easyparkbackend.controller;

import com.oocl.easyparkbackend.model.ParkingLot;
import com.oocl.easyparkbackend.service.ParkingLotService;
import io.restassured.http.ContentType;
import io.restassured.mapper.TypeRef;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotServiceTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ParkingLotController parkingLotController;
    private ParkingLotService parkingLotService;

    private List<ParkingLot> parkingLotList;
    private ParkingLot parkingLot;

    private Root<ParkingLot> parkingLotRoot;
    private Predicate predicate;
    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<ParkingLot> query;
    private EntityManager entityManager;
    TypedQuery<ParkingLot> typedQuery;

    private static String HOUR_RATE = "hourRate";
    private static String DISTANCE = "distance";
    private static String RATING = "rating";
    private static String ASC_ORDER = "asc";
    private static String DESC_ORDER = "desc";

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(parkingLotController);


        parkingLotRoot = Mockito.mock(Root.class);
        predicate = Mockito.mock(Predicate.class);
        criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
        query = Mockito.mock(CriteriaQuery.class);
        entityManager = Mockito.mock(EntityManager.class);
        typedQuery = Mockito.mock(TypedQuery.class);
        parkingLotService = new ParkingLotService(entityManager);

        parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1, "A", 10, 10, 10.0, 100.0, 2.0));
        parkingLotList.add(new ParkingLot(2, "B", 10, 10, 20.0, 200.0, 4.0));
        parkingLotList.add(new ParkingLot(3, "C", 10, 10, 30.0, 300.0, 5.0));

        parkingLot = parkingLotList.get(0);

    }


    @Test
    public void should_run_greate_than_hour_rate_statement_and_find_result_if_parkinglot_hour_rate_is_10_and_price_From_is_10() throws Exception {

        Double priceFrom = 10.0;

        List<ParkingLot> resultList = new ArrayList<>();
        resultList.add(parkingLotList.get(0));

        Mockito.when(entityManager.getCriteriaBuilder())
                .thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createQuery(ParkingLot.class))
                .thenReturn(query);
        Mockito.when(query.from(ParkingLot.class))
                .thenReturn(parkingLotRoot);
        Mockito.when(criteriaBuilder.ge(parkingLotRoot.get(HOUR_RATE),
                priceFrom)).thenReturn(predicate);
        Mockito.when(entityManager.createQuery(query))
                .thenReturn(typedQuery);
        Mockito.when(typedQuery.getResultList()).thenReturn(resultList);
        List<ParkingLot> parkingLots = parkingLotService.findAll(priceFrom, null, null, null);
        Assert.assertThat(resultList, CoreMatchers.is(parkingLots));
        verify(criteriaBuilder, times(1)).ge(parkingLotRoot.get(HOUR_RATE), priceFrom);

    }

    @Test
    public void should_run_less_than_hour_rate_statement_and_find_result_if_parkinglot_hour_rate_is_10_and_price_To_is_10() throws Exception {

        Double priceTo = 10.0;

        List<ParkingLot> resultList = new ArrayList<>();
        resultList.add(parkingLotList.get(0));

        Mockito.when(entityManager.getCriteriaBuilder())
                .thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createQuery(ParkingLot.class))
                .thenReturn(query);
        Mockito.when(query.from(ParkingLot.class))
                .thenReturn(parkingLotRoot);
        Mockito.when(criteriaBuilder.le(parkingLotRoot.get(HOUR_RATE),
                priceTo)).thenReturn(predicate);
        Mockito.when(entityManager.createQuery(query))
                .thenReturn(typedQuery);
        Mockito.when(typedQuery.getResultList()).thenReturn(resultList);
        List<ParkingLot> parkingLots = parkingLotService.findAll(null, priceTo, null, null);
        Assert.assertThat(resultList, CoreMatchers.is(parkingLots));
        verify(criteriaBuilder, times(1)).le(parkingLotRoot.get(HOUR_RATE), priceTo);

    }

    @Test
    public void should_run_less_than_distance_statement_and_find_result_if_parkinglot_distance_is_100_and_distance_is_100() throws Exception {

        Double distance = 100.0;

        List<ParkingLot> resultList = new ArrayList<>();
        resultList.add(parkingLotList.get(0));

        Mockito.when(entityManager.getCriteriaBuilder())
                .thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createQuery(ParkingLot.class))
                .thenReturn(query);
        Mockito.when(query.from(ParkingLot.class))
                .thenReturn(parkingLotRoot);
        Mockito.when(criteriaBuilder.le(parkingLotRoot.get(DISTANCE),
                distance)).thenReturn(predicate);
        Mockito.when(entityManager.createQuery(query))
                .thenReturn(typedQuery);
        Mockito.when(typedQuery.getResultList()).thenReturn(resultList);
        List<ParkingLot> parkingLots = parkingLotService.findAll(null, null, distance, null);
        Assert.assertThat(resultList, CoreMatchers.is(parkingLots));
        verify(criteriaBuilder, times(1)).le(parkingLotRoot.get(DISTANCE), distance);

    }

    @Test
    public void should_sorting_if_ratingOrder_is_asc() throws Exception {

        List<ParkingLot> resultList = new ArrayList<>();
        resultList.add(parkingLotList.get(0));

        Mockito.when(entityManager.getCriteriaBuilder())
                .thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createQuery(ParkingLot.class))
                .thenReturn(query);
        Mockito.when(query.from(ParkingLot.class))
                .thenReturn(parkingLotRoot);
        Mockito.when(entityManager.createQuery(query))
                .thenReturn(typedQuery);
        Mockito.when(typedQuery.getResultList()).thenReturn(resultList);

        List<ParkingLot> parkingLots = parkingLotService.findAll(null, null, null, ASC_ORDER);
        Assert.assertThat(resultList, CoreMatchers.is(parkingLots));
        verify(query, times(1)).orderBy(criteriaBuilder.asc(parkingLotRoot.get(RATING)));

    }

    @Test
    public void should_sorting_if_ratingOrder_is_desc() throws Exception {

        List<ParkingLot> resultList = new ArrayList<>();
        resultList.add(parkingLotList.get(0));

        Mockito.when(entityManager.getCriteriaBuilder())
                .thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createQuery(ParkingLot.class))
                .thenReturn(query);
        Mockito.when(query.from(ParkingLot.class))
                .thenReturn(parkingLotRoot);
        Mockito.when(entityManager.createQuery(query))
                .thenReturn(typedQuery);
        Mockito.when(typedQuery.getResultList()).thenReturn(resultList);

        List<ParkingLot> parkingLots = parkingLotService.findAll(null, null, null, DESC_ORDER);
        Assert.assertThat(resultList, CoreMatchers.is(parkingLots));
        verify(query, times(1)).orderBy(criteriaBuilder.desc(parkingLotRoot.get(RATING)));

    }

}