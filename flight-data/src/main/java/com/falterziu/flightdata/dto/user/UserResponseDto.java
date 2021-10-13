package com.falterziu.flightdata.dto.user;

import com.falterziu.flightdata.enumeration.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private Integer id;

    private String firstName;


    private String lastName;


    private String password;


    private String email;


    private Role role;
}
