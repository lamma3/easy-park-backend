package com.oocl.easyparkbackend.controller;

import com.oocl.easyparkbackend.model.ParkingLot;
import com.oocl.easyparkbackend.repository.ParkingLotRepository;
import com.oocl.easyparkbackend.service.ParkingLotService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotServiceTest {

    @Mock
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private ParkingLotService parkingLotService;

    private List<ParkingLot> parkingLotList;

    private Stream<ParkingLot> parkingLotStream;
    private static String HOUR_RATE = "hourRate";
    private static String DISTANCE = "distance";
    private static String RATING = "rating";
    private static String ASC_ORDER = "asc";
    private static String DESC_ORDER = "desc";

    @Before
    public void setUp() throws Exception {

        parkingLotService = new ParkingLotService(parkingLotRepository);
        parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1,"A","AddressA",111.111111,111.111111,56.5,100.0,10,10,0,0,0,0,0,0,1.0));
        parkingLotList.add(new ParkingLot(2,"B","AddressB",222.222222,222.222222,60.5,200.0,10,10,0,0,0,0,0,0,2.0));
        parkingLotList.add(new ParkingLot(3,"C","AddressC",333.333333,333.333333,75.5,300.0,10,10,0,0,0,0,0,0,3.0));

        parkingLotStream = mock(Stream.class);
    }

    @Test
    public void should_run_greate_than_hour_rate_statement_and_find_result_if_parkinglot_hour_rate_is_10_and_price_From_is_10() throws Exception {

        Double priceFrom = 10.0;

        List<ParkingLot> resultList = new ArrayList<>();
        resultList.add(parkingLotList.get(0));
        doReturn(resultList).when(parkingLotRepository).findAll();

        List<ParkingLot> parkingLots = parkingLotService.findAll(priceFrom, null, null, null);
        Assert.assertThat(resultList, CoreMatchers.is(parkingLots));


    }

    @Test
    public void should_run_less_than_hour_rate_statement_and_find_result_if_parkinglot_hour_rate_is_10_and_price_To_is_10() throws Exception {

        Double priceTo = 56.5;

        List<ParkingLot> resultList = new ArrayList<>();
        resultList.add(parkingLotList.get(0));
        doReturn(resultList).when(parkingLotRepository).findAll();

        List<ParkingLot> parkingLots = parkingLotService.findAll(priceTo, null, null, null);
        Assert.assertThat(resultList, CoreMatchers.is(parkingLots));

    }


    @Test
    public void should_sorting_if_ratingOrder_is_asc() throws Exception {

        List<ParkingLot> resultList = parkingLotList;
        doReturn(resultList).when(parkingLotRepository).findAll();

        List<ParkingLot> parkingLots = parkingLotService.findAll(null, null, null, ASC_ORDER);
        Assert.assertThat(resultList, CoreMatchers.is(parkingLots));

    }

    @Test
    public void should_sorting_if_ratingOrder_is_desc() throws Exception {

        List<ParkingLot> resultList = parkingLotList;
        doReturn(resultList).when(parkingLotRepository).findAll();
        List<ParkingLot> parkingLots = parkingLotService.findAll(null, null, null, DESC_ORDER);
        resultList.sort(Comparator.comparingDouble(ParkingLot::getRating).reversed());
        Assert.assertThat(resultList, CoreMatchers.is(parkingLots));

    }

}