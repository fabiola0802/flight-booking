package com.falterziu.flightdata.util;

public enum NotFound {

    USER_NOT_FOUND ("User not found"),
    CLASS_NOT_FOUND("Class not found"),
    FLIGHT_NOT_FOUND("Flight not found");


    private String message;

    public String getMessage() {
        return message;
    }

    private NotFound(String message) {
        this.message = message;
    }

}
