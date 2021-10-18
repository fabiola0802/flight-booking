package com.falterziu.flightdata.dto.notification;

import com.falterziu.flightdata.dto.booking.BookingResponseDto;
import com.falterziu.flightdata.enumeration.Type;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationResponseDto {


    private String message;

    private LocalDateTime createdOn;

    private Type type;

    private BookingResponseDto booking;
}
