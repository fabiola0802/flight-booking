package com.falterziu.flightdata.exception;

import com.falterziu.flightdata.util.NotFound;

public class FlightAppNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public FlightAppNotFoundException(NotFound notFound) {
        super(notFound.getMessage());
    }
}
