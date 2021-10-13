package com.falterziu.flightdata.security;

import com.falterziu.flightdata.enumeration.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String token;
}
