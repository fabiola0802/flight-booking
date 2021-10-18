package com.falterziu.flightdata.service;

import com.falterziu.flightdata.dto.notification.NotificationResponseDto;

import java.util.List;

public interface NotificationService {

      List<NotificationResponseDto> getAllUserNotifications(String email);
}
