package com.falterziu.flightdata.mapper;


import com.falterziu.flightdata.dto.flight_class.FlightClassDto;
import com.falterziu.flightdata.dto.flight_class.FlightClassResponseDto;
import com.falterziu.flightdata.entity.FlightClassEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightClassMapper {

    @Mapping(target="classEntity.id", source="flightClassDto.classId")
    FlightClassEntity toEntity (FlightClassDto flightClassDto);

    @Mapping(target="classDto", source="classEntity")
    FlightClassResponseDto toDto (FlightClassEntity flightClassEntity);
    List<FlightClassResponseDto> toDtoList(List<FlightClassEntity> flightClassEntities);



}
