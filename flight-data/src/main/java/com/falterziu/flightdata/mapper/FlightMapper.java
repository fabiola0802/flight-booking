package com.falterziu.flightdata.mapper;

import com.falterziu.flightdata.dto.flight.FlightDto;
import com.falterziu.flightdata.dto.flight.FlightResponseDto;
import com.falterziu.flightdata.entity.FlightEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses = FlightClassMapper.class)
public interface FlightMapper {


    FlightResponseDto toDto (FlightEntity flightEntity);

    @Mapping(target = "flightClasses", ignore = true)
    FlightEntity toEntity(FlightDto flightDto);
    default Page<FlightResponseDto> toPageDto(Page<FlightEntity> users) {
        return users.map(this::toDto);
    }
}
