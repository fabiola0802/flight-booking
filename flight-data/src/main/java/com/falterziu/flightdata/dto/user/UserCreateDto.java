package com.falterziu.flightdata.dto.user;

import com.falterziu.flightdata.enumeration.Role;
import com.falterziu.flightdata.util.Constant;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;


@Getter
@Setter
public class UserCreateDto {

    @Pattern(regexp = Constant.FORMAT , message = "Wrong name format")
    private String firstName;

    @Pattern(regexp = Constant.FORMAT, message = "Wrong surname format")
    private String lastName;

    @Pattern(regexp = Constant.PASSWORD_REGEX,
            message = "Password must be between 6 - 16 characters, one upper case character, have numbers and a special character")
    private String password;

    @Pattern(regexp = Constant.EMAIL_REGEX, message = "Wrong email format")
    private String email;

    private Role role;

}
