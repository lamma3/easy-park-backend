package com.oocl.easyparkbackend.model;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "4.0")
    private Double score;
    @ApiModelProperty(example = "4")
    private Integer parkingLotId;

    @ManyToOne
    @JoinColumn(name = "parkingLotId", updatable = false, insertable = false)
    private ParkingLot parkingLot;
}
