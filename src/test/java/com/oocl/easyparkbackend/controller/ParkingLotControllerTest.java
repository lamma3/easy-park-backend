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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ParkingLotController parkingLotController;

    List<ParkingLot> parkingLotList;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(parkingLotController);

        parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1, "A", 10, 10, 10.0, 100.0, 2.0));
        parkingLotList.add(new ParkingLot(2, "B", 10, 10, 20.0, 200.0, 4.0));
        parkingLotList.add(new ParkingLot(3, "C", 10, 10, 30.0, 300.0, 5.0));
    }


    @Test
    public void should_allow_frontend_to_call_api() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/parking-lots")
                .header("Access-Control-Request-Method", "GET")
                .header("Origin", "http://localhost:3000"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_not_allow_unknown_host_to_call_api() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/parking-lots")
                .header("Access-Control-Request-Method", "GET")
                .header("Origin", "http://unknown"))
                .andExpect(status().isForbidden());
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
        Assert.assertEquals("A", parkingLot.getName());

    }
}