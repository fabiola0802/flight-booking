package com.falterziu.flightdata.dto.booking;

import com.falterziu.flightdata.dto.flight_class.FlightClassResponseDto;
import com.falterziu.flightdata.dto.user.UserResponseDto;
import com.falterziu.flightdata.util.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingResponseDto {

    private Integer id;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = Constant.DATE_PATTERN)
    private LocalDate bookingDate;

    private String status;

    private FlightClassResponseDto flightClassDto;

    private UserResponseDto user;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String note;
}
