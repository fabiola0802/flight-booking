package com.falterziu.flightweb.controller;

import com.falterziu.flightdata.dto.notification.NotificationResponseDto;
import com.falterziu.flightdata.service.NotificationService;
import com.falterziu.flightdata.util.AuthenticationFacade;
import com.falterziu.flightdata.util.Routes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = Routes.NOTIFICATIONS)
public class NotificationController {

    private final NotificationService notificationService;
    private final AuthenticationFacade authenticationFacade;

    public NotificationController(NotificationService notificationService, AuthenticationFacade authenticationFacade) {
        this.notificationService = notificationService;
        this.authenticationFacade= authenticationFacade;
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponseDto>> getAllUserNotifications(){
        String email  = authenticationFacade.getUserPrincipal().getEmail();
        return ResponseEntity.ok(notificationService.getAllUserNotifications(email));
    }

}
