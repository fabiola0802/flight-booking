package com.falterziu.flightdata.entity;

import com.falterziu.flightdata.enumeration.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "notification")
public class NotificationEntity extends BaseEntity {
    @Column
    private String message;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Enumerated(EnumType.STRING)
    @Column
    private Type type;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;

}
