package com.falterziu.flightdata.mapper;


import com.falterziu.flightdata.dto.flight_class.FlightClassDto;
import com.falterziu.flightdata.entity.FlightClassEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightClassMapper {

    @Mapping(target="classEntity.id", source="flightClassDto.classId")
    FlightClassEntity toEntity (FlightClassDto flightClassDto);

    @Mapping(target="classId", source="flightClassEntity.id")
    FlightClassDto toDto (FlightClassEntity flightClassEntity);
    List<FlightClassDto> toDtoList(List<FlightClassEntity> flightClassEntities);
    List<FlightClassEntity> toEntityList(List<FlightClassDto> flightClassDtoList);


}
