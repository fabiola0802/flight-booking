package com.falterziu.flightdata.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "flight")
public class FlightEntity extends BaseEntity {

    @Column
    private String departure;

    @Column
    private String destination;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column
    private Integer capacity;

    @Column
    private Boolean validity;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.PERSIST)
    private List<FlightClassEntity> flightClasses = new ArrayList<>();


    public void addFlightClass(FlightClassEntity flight){
        flightClasses.add(flight);
        flight.setFlight(this);
    }

    @Override
    public String toString() {
        return "FlightEntity{" +
                "departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", capacity=" + capacity +
                ", validity=" + validity +
                '}';
    }
}
