package com.oocl.easyparkbackend.model;

import static java.lang.Math.*;


public class DistanceCalculator {


    public static final int METER_UNIT = 6371000;

    public double distance(double latitudeA, double longitudeA, double latitudeB, double longitudeB) {
        latitudeA = toRadians(latitudeA);
        longitudeA = toRadians(longitudeA);
        latitudeB = toRadians(latitudeB);
        longitudeB = toRadians(longitudeB);

        double longitudeDistance = longitudeB - longitudeA;
        double latitudeDistance = latitudeB - latitudeA;

        double distance = pow(sin(latitudeDistance / 2), 2) +
                cos(latitudeA) * cos(latitudeB) *
                        pow(sin(longitudeDistance / 2), 2);

        distance = 2 * asin(sqrt(distance));
        System.out.println(distance * METER_UNIT);
        return distance * METER_UNIT;
    }
}
