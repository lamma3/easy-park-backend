package com.oocl.easyparkbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ParkingLotBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String status;
    private Integer parkingLotId;

    @OneToOne
    @JoinColumn(name = "parkingLotId", updatable = false, insertable = false)
    private ParkingLot parkingLot;
}