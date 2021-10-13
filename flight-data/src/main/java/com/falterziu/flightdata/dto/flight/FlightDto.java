package com.falterziu.flightdata.dto.flight;

import com.falterziu.flightdata.dto.flight_class.FlightClassDto;
import com.falterziu.flightdata.util.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class FlightDto {


    @NotEmpty
    @Size(min=4, max=20, message = "Departure length must be between 4 and 20 characters")
    private String departure;

    @NotEmpty
    @Size(min=4, max=20, message = "Destination length must be between 4 and 20 characters")
    private String destination;

    @JsonFormat(pattern = Constant.DATE_TIME_PATTERN)
    private LocalDateTime departureTime;

    @JsonFormat(pattern = Constant.DATE_TIME_PATTERN)
    private LocalDateTime arrivalTime;

    @Min(value = 20, message = "Minimum capacity is 20")
    @Max(value = 50, message = "Maximum capacity is 50!")
    private Integer capacity;

    @NotNull
    private List<FlightClassDto> flightClasses;
}
