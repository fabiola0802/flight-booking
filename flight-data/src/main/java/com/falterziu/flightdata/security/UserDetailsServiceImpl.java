package com.falterziu.flightdata.security;

import com.falterziu.flightdata.entity.UserEntity;
import com.falterziu.flightdata.exception.FlightAppNotFoundException;
import com.falterziu.flightdata.repository.UserRepository;
import com.falterziu.flightdata.util.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent()) {
            throw new FlightAppNotFoundException(NotFound.USER_NOT_FOUND);
        }
        return UserPrincipal.build(optionalUser.get());
    }
}