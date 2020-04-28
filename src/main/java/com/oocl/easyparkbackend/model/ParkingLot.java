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

    public void update(ParkingLot parkingLot) {
        if(parkingLot.getName() != null){
            this.setName(parkingLot.getName());
        }
        if(parkingLot.getAddress() != null){
            this.setAddress(parkingLot.getAddress());
        }
        if(parkingLot.getLatitude() != null){
            this.setLatitude(parkingLot.getLatitude());
        }
        if(parkingLot.getLongitude() != null){
            this.setLongitude(parkingLot.getLongitude());
        }
        if(parkingLot.getHourRate() != null){
            this.setHourRate(parkingLot.getHourRate());
        }
        if(parkingLot.getTotalCapacity() != null){
            this.setTotalCapacity(parkingLot.getTotalCapacity());
        }
        if(parkingLot.getAvailableCapacity() != null){
            this.setAvailableCapacity(parkingLot.getAvailableCapacity());
        }
        if(parkingLot.getOccupiedCapacity() != null){
            this.setOccupiedCapacity(parkingLot.getOccupiedCapacity());
        }
        if(parkingLot.getReservedCapacity() != null){
            this.setReservedCapacity(parkingLot.getReservedCapacity());
        }
        if(parkingLot.getTotalChargeCapacity() != null){
            this.setTotalChargeCapacity(parkingLot.getTotalChargeCapacity());
        }
        if(parkingLot.getOccupiedChargeCapacity() != null){
            this.setOccupiedChargeCapacity(parkingLot.getOccupiedChargeCapacity());
        }
        if(parkingLot.getAvailableChargeCapacity() != null){
            this.setAvailableChargeCapacity(parkingLot.getAvailableChargeCapacity());
        }
        if(parkingLot.getReservedChargeCapacity() != null){
            this.setReservedChargeCapacity(parkingLot.getReservedChargeCapacity());
        }


    }
}
