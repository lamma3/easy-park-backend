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

        double lat1 = 53.32055555555556;
        double lat2 = 53.31861111111111;
        double lon1 = -1.7297222222222221;
        double lon2 = -1.6997222222222223;

        DistanceCalculator distanceCalculator = new DistanceCalculator();
        int result = distanceCalculator.distance(lon1,lat1,lon2,lat2);
        System.out.println(result);
    }
}