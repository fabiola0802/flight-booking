package com.falterziu.flightdata.exception;

import com.falterziu.flightdata.util.BadRequest;
import com.falterziu.flightdata.util.NotFound;

public class FlightAppBadRequestException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public FlightAppBadRequestException(BadRequest badRequest) {
        super(badRequest.getMessage());
    }
}
