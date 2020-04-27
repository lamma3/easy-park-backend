package com.oocl.easyparkbackend.controller;

import com.oocl.easyparkbackend.model.DistanceCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistanceCalculatorTest {
    @Test
    public void should_return_the_distance_between_two_location_when_given_the_longitude_and_latitude() {
        double longitudeA = 114.212276;
        double longitudeB = 114.200638;
        double latitudeA = 22.425931;
        double latitudeB = 22.39438;


        DistanceCalculator distanceCalculator = new DistanceCalculator();
        double result = distanceCalculator.distance(latitudeA, longitudeA, latitudeB, longitudeB);
        System.out.println(result);
    }
}