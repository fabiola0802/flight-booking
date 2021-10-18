package com.falterziu.flightdata.dto.booking;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BookingDto {

    @NotNull
    private Integer flightClassId;

}
