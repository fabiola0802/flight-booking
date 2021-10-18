package com.falterziu.flightdata.dto.user;

import com.falterziu.flightdata.enumeration.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    @JsonIgnore
    private String password;

    private Role role;

    private Integer leftFlights;

}
