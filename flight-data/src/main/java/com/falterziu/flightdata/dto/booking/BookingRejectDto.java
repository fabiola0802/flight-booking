package com.falterziu.flightdata.dto.booking;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class BookingRejectDto {

    @NotEmpty
    private String note;
}
