package com.oocl.easyparkbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date utilTimestamp;
    private String status;
    private Boolean isElectricCar;
    private Integer parkingLotId;

    @OneToOne
    @JoinColumn(name = "parkingLotId", updatable = false, insertable = false)
    private ParkingLot parkingLot;
}