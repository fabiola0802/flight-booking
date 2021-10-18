package com.falterziu.flightdata.mapper;

import com.falterziu.flightdata.dto.notification.NotificationResponseDto;
import com.falterziu.flightdata.entity.NotificationEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    List<NotificationResponseDto> toListDto (List<NotificationEntity> notificationEntityList);

}
