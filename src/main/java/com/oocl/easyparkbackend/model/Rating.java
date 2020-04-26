package com.oocl.easyparkbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private Integer id;
    private Double score;
    private Integer parkingLotId;
    private ParkingLot parkingLot;
}
