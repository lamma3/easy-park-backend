package com.oocl.easyparkbackend.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingLot {
    private Integer id;
    private String name;
    private Integer totalCapacity;
    private Integer availableCapacity;
}
