package com.falterziu.flightdata.security;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequest {

    private String token;
}
