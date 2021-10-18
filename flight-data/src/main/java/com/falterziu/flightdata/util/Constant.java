package com.falterziu.flightdata.util;

public class Constant {

    public static final String FORMAT = "^[a-zA-Z0-9]{3,15}";
    public static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\wds:])([^\\s]){6,16}$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,6}";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String APPROVED_BOOKING = "Your booking has been approved";
    public static final String REJECTED_BOOKING = "Your booking has been rejected";
    public static Integer maxFlightsPerYear = 20;
;}
