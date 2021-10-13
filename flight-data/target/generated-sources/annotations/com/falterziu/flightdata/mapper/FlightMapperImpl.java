package com.falterziu.flightdata.mapper;

import com.falterziu.flightdata.dto.flight.FlightDto;
import com.falterziu.flightdata.dto.flight.FlightResponseDto;
import com.falterziu.flightdata.dto.flight_class.FlightClassDto;
import com.falterziu.flightdata.entity.FlightClassEntity;
import com.falterziu.flightdata.entity.FlightEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-13T22:41:00+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class FlightMapperImpl implements FlightMapper {

    @Override
    public FlightResponseDto toDto(FlightEntity flightEntity) {
        if ( flightEntity == null ) {
            return null;
        }

        FlightResponseDto flightResponseDto = new FlightResponseDto();

        flightResponseDto.setId( flightEntity.getId() );
        flightResponseDto.setDeparture( flightEntity.getDeparture() );
        flightResponseDto.setDestination( flightEntity.getDestination() );
        flightResponseDto.setDepartureTime( flightEntity.getDepartureTime() );
        flightResponseDto.setArrivalTime( flightEntity.getArrivalTime() );
        flightResponseDto.setCapacity( flightEntity.getCapacity() );
        flightResponseDto.setFlightClasses( flightClassEntityListToFlightClassDtoList( flightEntity.getFlightClasses() ) );

        return flightResponseDto;
    }

    @Override
    public FlightEntity toEntity(FlightDto flightDto) {
        if ( flightDto == null ) {
            return null;
        }

        FlightEntity flightEntity = new FlightEntity();

        flightEntity.setDeparture( flightDto.getDeparture() );
        flightEntity.setDestination( flightDto.getDestination() );
        flightEntity.setDepartureTime( flightDto.getDepartureTime() );
        flightEntity.setArrivalTime( flightDto.getArrivalTime() );
        flightEntity.setCapacity( flightDto.getCapacity() );

        return flightEntity;
    }

    protected FlightClassDto flightClassEntityToFlightClassDto(FlightClassEntity flightClassEntity) {
        if ( flightClassEntity == null ) {
            return null;
        }

        FlightClassDto flightClassDto = new FlightClassDto();

        flightClassDto.setPrice( flightClassEntity.getPrice() );
        flightClassDto.setCapacity( flightClassEntity.getCapacity() );

        return flightClassDto;
    }

    protected List<FlightClassDto> flightClassEntityListToFlightClassDtoList(List<FlightClassEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<FlightClassDto> list1 = new ArrayList<FlightClassDto>( list.size() );
        for ( FlightClassEntity flightClassEntity : list ) {
            list1.add( flightClassEntityToFlightClassDto( flightClassEntity ) );
        }

        return list1;
    }
}
