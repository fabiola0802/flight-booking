package com.falterziu.flightdata.util;

import com.falterziu.flightdata.exception.FlightAppBadRequestException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public  class DateTimeConversionUtil {

    public static LocalDate convertStringDateToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_PATTERN);
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException ex) {
            throw new FlightAppBadRequestException(BadRequest.WRONG_DATES);
        }
    }
}
