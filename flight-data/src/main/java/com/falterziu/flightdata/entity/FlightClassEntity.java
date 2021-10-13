package com.falterziu.flightdata.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@Table(name="flight_class")
public class FlightClassEntity extends BaseEntity{

    private BigDecimal price;

    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private FlightEntity flight;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;
}
