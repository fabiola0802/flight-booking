package com.falterziu.flightdata.service.impl;

import com.falterziu.flightdata.dto.notification.NotificationResponseDto;
import com.falterziu.flightdata.mapper.NotificationMapper;
import com.falterziu.flightdata.repository.NotificationRepository;
import com.falterziu.flightdata.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public List<NotificationResponseDto> getAllUserNotifications(String email) {
        log.debug("Listing all notification for user with email {}" , email);
        return notificationMapper.toListDto(notificationRepository.getAllNotificationsOfUser(email));
    }
}
