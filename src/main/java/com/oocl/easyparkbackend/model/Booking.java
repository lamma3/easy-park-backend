package com.oocl.easyparkbackend.model;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(example = "1")
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date utilTimestamp;
    @ApiModelProperty(example = "reserved")
    private String status;
    @ApiModelProperty(example = "true")
    private Boolean isElectricCar;
    @ApiModelProperty(example = "4")
    private Integer parkingLotId;

    @OneToOne
    @JoinColumn(name = "parkingLotId", updatable = false, insertable = false)
    private ParkingLot parkingLot;
}
