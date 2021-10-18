package com.falterziu.flightdata.dto.user;

import com.falterziu.flightdata.util.Constant;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ChangePasswordDto {

    @NotBlank
    String oldPassword;

    @Pattern(regexp = Constant.PASSWORD_REGEX)
    String newPassword;
}
