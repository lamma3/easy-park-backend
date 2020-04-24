package com.oocl.easyparkbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FilterParkingLotCondition
{
    Integer minPrice;
    Integer maxPrice;

    Double minDistance;
    Double maxDistance;

    Boolean needElectricCharge;

    Double minRating;
    Double maxRating;
}
