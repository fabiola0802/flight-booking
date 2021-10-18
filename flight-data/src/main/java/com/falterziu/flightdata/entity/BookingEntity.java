package com.falterziu.flightdata.entity;


import com.falterziu.flightdata.enumeration.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class BookingEntity extends BaseEntity{

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;

    @Column
    private String note;

    @ManyToOne
    @JoinColumn(name = "flight_class_id")
    private FlightClassEntity flightClassEntity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserEntity user;

    @Column
    private Boolean validity;

}
