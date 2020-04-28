package com.oocl.easyparkbackend.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @ApiModelProperty(example = "4")
    private Integer id;
    @ApiModelProperty(example = "Fortune City One Car Park")
    private String name;
    @ApiModelProperty(example = "1 Ngan Shing St, Sha Tin")
    private String address;
    @ApiModelProperty(example = "22.386228")
    private Double latitude;
    @ApiModelProperty(example = "114.203594")
    private Double longitude;
    @ApiModelProperty(example = "24.0")
    private Double hourRate;
    @ApiModelProperty(example = "120.0")
    private Double distance;
    @ApiModelProperty(example = "10")
    private Integer totalCapacity;
    @ApiModelProperty(example = "10")
    private Integer availableCapacity;
    @ApiModelProperty(example = "0")
    private Integer occupiedCapacity;
    @ApiModelProperty(example = "0")
    private Integer reservedCapacity;
    @ApiModelProperty(example = "40")
    private Integer totalChargeCapacity;
    @ApiModelProperty(example = "40")
    private Integer availableChargeCapacity;
    @ApiModelProperty(example = "0")
    private Integer occupiedChargeCapacity;
    @ApiModelProperty(example = "0")
    private Integer reservedChargeCapacity;
    @ApiModelProperty(example = "4.0")
    private Double rating;

    public void update(ParkingLot parkingLot) {
        if (parkingLot.getName() != null) {
            this.setName(parkingLot.getName());
        }
        if (parkingLot.getAddress() != null) {
            this.setAddress(parkingLot.getAddress());
        }
        if (parkingLot.getLatitude() != null) {
            this.setLatitude(parkingLot.getLatitude());
        }
        if (parkingLot.getLongitude() != null) {
            this.setLongitude(parkingLot.getLongitude());
        }
        if (parkingLot.getHourRate() != null) {
            this.setHourRate(parkingLot.getHourRate());
        }
        if (parkingLot.getTotalCapacity() != null) {
            this.setTotalCapacity(parkingLot.getTotalCapacity());
        }
        if (parkingLot.getAvailableCapacity() != null) {
            this.setAvailableCapacity(parkingLot.getAvailableCapacity());
        }
        if (parkingLot.getOccupiedCapacity() != null) {
            this.setOccupiedCapacity(parkingLot.getOccupiedCapacity());
        }
        if (parkingLot.getReservedCapacity() != null) {
            this.setReservedCapacity(parkingLot.getReservedCapacity());
        }
        if (parkingLot.getTotalChargeCapacity() != null) {
            this.setTotalChargeCapacity(parkingLot.getTotalChargeCapacity());
        }
        if (parkingLot.getOccupiedChargeCapacity() != null) {
            this.setOccupiedChargeCapacity(parkingLot.getOccupiedChargeCapacity());
        }
        if (parkingLot.getAvailableChargeCapacity() != null) {
            this.setAvailableChargeCapacity(parkingLot.getAvailableChargeCapacity());
        }
        if (parkingLot.getReservedChargeCapacity() != null) {
            this.setReservedChargeCapacity(parkingLot.getReservedChargeCapacity());
        }


    }
}
