package com.falterziu.flightdata.util;

public enum BadRequest {

   USER_EXISTS("User already exists!"),
    CAN_NOT_BOOK_ANYMORE("You have exceeded the allowed number of flights for this year"),
    CLASS_EXISTS("Class already exists"),
    NOT_ALLOWED("You are not allowed to perform this action"),
   WRONG_DATES("Dates are not valid"),
   DUPLICATED_CLASSES ("Classes are duplicated!"),
   WRONG_CAPACITIES ("Class capacities are greater than total capacity!"),
    PASSWORD_SAME_AS_OLD("New password can't be same as old password"),
    OLD_PASS_NOT_MATCH("Old password does not match"),
    MAX_FLIGHTS_EXCEEDED("You are not allowed to book more than 20 flights per year"),
    WRONG_FORMAT("Wrong date format");



    private String message;

    public String getMessage() {
        return message;
    }

    private BadRequest(String message) {
        this.message = message;
    }
}
