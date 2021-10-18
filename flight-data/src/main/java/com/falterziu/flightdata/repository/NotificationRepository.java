package com.falterziu.flightdata.repository;

import com.falterziu.flightdata.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {

     @Query("select n from #{#entityName}  n where n.booking.user.email = ?1")
     List<NotificationEntity> getAllNotificationsOfUser(String email);
}
