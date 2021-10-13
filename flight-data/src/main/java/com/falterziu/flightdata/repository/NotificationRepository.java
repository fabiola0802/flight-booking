package com.falterziu.flightdata.repository;

import com.falterziu.flightdata.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
}
