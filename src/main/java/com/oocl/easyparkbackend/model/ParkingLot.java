package com.oocl.easyparkbackend.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLot {
    private Integer parkingLotId;
    private String parkingLotName;
    private Integer totalCapacity;
    private Integer availableCapacity;
}
