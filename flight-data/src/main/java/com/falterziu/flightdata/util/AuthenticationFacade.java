package com.falterziu.flightdata.util;

import com.falterziu.flightdata.dto.user.UserResponseDto;
import com.falterziu.flightdata.mapper.UserMapper;
import com.falterziu.flightdata.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

    private final UserMapper userMapper;

    public AuthenticationFacade(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public UserPrincipal getUserPrincipal() {
        return (UserPrincipal) getAuthentication().getPrincipal();
    }

    public UserResponseDto getUserDto() {
        return userMapper.toDto(getUserPrincipal());
    }
}
