package com.falterziu.flightdata.dto.flight_class;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Getter
@Setter
public class FlightClassDto {


    private BigDecimal price;

    @Min(value =20, message = "Minimum capacity is 20")
    @Max(value = 50, message = "Maximum capacity is 50!")
    private Integer capacity;

    private Integer classId;

}
