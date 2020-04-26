package com.oocl.easyparkbackend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private Double hourRate;
    private Double distance;
    private Integer totalCapacity;
    private Integer availableCapacity;
    private Integer occupiedCapacity;
    private Integer reservedCapacity;
    private Integer totalChargeCapacity;
    private Integer availableChargeCapacity;
    private Integer occupiedChargeCapacity;
    private Integer reservedChargeCapacity;
    private Double rating;


}
