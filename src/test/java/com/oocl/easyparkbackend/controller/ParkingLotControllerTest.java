package com.oocl.easyparkbackend.controller;

import com.oocl.easyparkbackend.model.ParkingLot;
import com.oocl.easyparkbackend.model.Rating;
import com.oocl.easyparkbackend.repository.ParkingLotRepository;
import com.oocl.easyparkbackend.repository.RatingRepository;
import io.restassured.http.ContentType;
import io.restassured.mapper.TypeRef;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ParkingLotController parkingLotController;

    @MockBean
    private ParkingLotRepository parkingLotRepository;
    @MockBean
    private RatingRepository ratingRepository;

    List<ParkingLot> parkingLotList;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(parkingLotController);

        parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1, "A", 10, 10, 10.0, 2.0, 2.0));
        parkingLotList.add(new ParkingLot(2, "B", 10, 10, 20.0, 0.0, 0.0));
        parkingLotList.add(new ParkingLot(3, "C", 10, 10, 30.0, 0.0, 0.0));
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

    @Test
    public void should_return_new_score_after_adding_new_rating() throws Exception {
        ParkingLot originalParkingLot = new ParkingLot(3, null, null, null, null, null, 0.0);
        ParkingLot updatedParkingLot = new ParkingLot(3, null, null, null, null, null, 4.0);
        Mockito.when(parkingLotRepository.findById(3))
                .thenReturn(Optional.of(originalParkingLot));
        Mockito.when(parkingLotRepository.save(Mockito.any()))
                .thenReturn(updatedParkingLot);

        Rating preSaveRating = new Rating(null, 4.0, 3, null);
        Rating savedRating = new Rating(1, 4.0, 3, null);
        Mockito.when(ratingRepository.save(Mockito.any()))
                .thenReturn(savedRating);

        mvc.perform(MockMvcRequestBuilders
                .post("/parking-lots/3/ratings")
                .content("{\"score\": 4}"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.score", is(4.0)))
                .andExpect(jsonPath("$.parkingLotId", is(3)))
                .andExpect(jsonPath("$.parkingLot.id", is(3)))
                .andExpect(jsonPath("$.parkingLot.averageScore", is(4)));

        Mockito.verify(parkingLotRepository, Mockito.times(1)).save(updatedParkingLot);
        Mockito.verify(ratingRepository, Mockito.times(1)).save(preSaveRating);
    }
}