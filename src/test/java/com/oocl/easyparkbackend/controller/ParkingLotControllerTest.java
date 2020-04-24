package com.oocl.easyparkbackend.controller;

import com.oocl.easyparkbackend.model.ParkingLot;
import io.restassured.http.ContentType;
import io.restassured.mapper.TypeRef;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingLotControllerTest {

    @Autowired
    private ParkingLotController parkingLotController;

    List<ParkingLot> parkingLotList;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(parkingLotController);

        parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1, "A", 10, 10));
        parkingLotList.add(new ParkingLot(2, "B", 10, 10));
        parkingLotList.add(new ParkingLot(3, "C", 10, 10));

    }

    @Test
    public void should_return_all_parking_lots() {
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/parking-lots");

        List<ParkingLot> parkingLots = response.getBody().as(new TypeRef<List<ParkingLot>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        Assert.assertEquals(3, parkingLots.size());
    }


    @Test
    public void should_return_parking_lot_by_id() {
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/parking-lots/1");

        ParkingLot parkingLot = response.getBody().as(ParkingLot.class);
        Assert.assertEquals("A", parkingLot.getParkingLotName());

    }
}