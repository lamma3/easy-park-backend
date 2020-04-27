package com.oocl.easyparkbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double score;
    private Integer parkingLotId;

    @ManyToOne
    @JoinColumn(name = "parkingLotId", updatable = false, insertable = false)
    private ParkingLot parkingLot;
}
