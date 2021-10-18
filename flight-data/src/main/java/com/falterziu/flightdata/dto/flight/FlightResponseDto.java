package com.falterziu.flightdata.dto.flight;

import com.falterziu.flightdata.dto.flight_class.FlightClassResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class FlightResponseDto {

    private Integer id;

    private String departure;


    private String destination;


    private LocalDateTime departureTime;


    private LocalDateTime arrivalTime;


    private Integer capacity;

    private List<FlightClassResponseDto> flightClasses;

}
