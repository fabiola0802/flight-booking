package com.falterziu.flightdata.dto.flight;

import com.falterziu.flightdata.util.DateTimeConversionUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class FlightFilter {

    private LocalDate from;
    private LocalDate to;

    public FlightFilter(String from, String to) {
        super();
        this.from = from != null ? DateTimeConversionUtil.convertStringDateToLocalDate(from) : null;
        this.to = to != null ? DateTimeConversionUtil.convertStringDateToLocalDate(to) : null;
    }
}
