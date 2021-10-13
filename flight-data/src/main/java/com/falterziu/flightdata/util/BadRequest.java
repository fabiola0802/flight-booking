package com.falterziu.flightdata.util;

public enum BadRequest {

   USER_EXISTS("User already exists!"),
    CLASS_EXISTS("Class already exists"),
   WRONG_DATES("Dates are not valid"),
   DUPLICATED_CLASSES ("Classes are duplicated!"),
   WRONG_CAPACITIES ("Class capacities are greater than total capacity!");


    private String message;

    public String getMessage() {
        return message;
    }

    private BadRequest(String message) {
        this.message = message;
    }
}
