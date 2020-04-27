package com.oocl.easyparkbackend.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DistanceCalculator {


    public static final int METER_UNIT = 6371000;

    public int distance(double longitudeA, double latitudeA, double longitudeB, double latitudeB) {
        longitudeA = Math.toRadians(longitudeA);
        latitudeA = Math.toRadians(latitudeA);
        longitudeB = Math.toRadians(longitudeB);
        latitudeB = Math.toRadians(latitudeB);

        // Haversine formula
        double longitudeDistance = longitudeB - longitudeA;
        double latitudeDistance = latitudeB - latitudeA;


        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(latitudeDistance / 2), 2)
                + Math.cos(latitudeA) * Math.cos(latitudeB)
                * Math.pow(Math.sin(longitudeDistance / 2), 2)));

        return (int)Math.round(distance * METER_UNIT);
    }
}
