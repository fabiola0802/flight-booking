package com.falterziu.flightdata.mapper;

import com.falterziu.flightdata.dto.flight_class.FlightClassDto;
import com.falterziu.flightdata.entity.ClassEntity;
import com.falterziu.flightdata.entity.FlightClassEntity;
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
public class FlightClassMapperImpl implements FlightClassMapper {

    @Override
    public FlightClassEntity toEntity(FlightClassDto flightClassDto) {
        if ( flightClassDto == null ) {
            return null;
        }

        FlightClassEntity flightClassEntity = new FlightClassEntity();

        flightClassEntity.setClassEntity( flightClassDtoToClassEntity( flightClassDto ) );
        flightClassEntity.setPrice( flightClassDto.getPrice() );
        flightClassEntity.setCapacity( flightClassDto.getCapacity() );

        return flightClassEntity;
    }

    @Override
    public FlightClassDto toDto(FlightClassEntity flightClassEntity) {
        if ( flightClassEntity == null ) {
            return null;
        }

        FlightClassDto flightClassDto = new FlightClassDto();

        flightClassDto.setClassId( flightClassEntity.getId() );
        flightClassDto.setPrice( flightClassEntity.getPrice() );
        flightClassDto.setCapacity( flightClassEntity.getCapacity() );

        return flightClassDto;
    }

    @Override
    public List<FlightClassDto> toDtoList(List<FlightClassEntity> flightClassEntities) {
        if ( flightClassEntities == null ) {
            return null;
        }

        List<FlightClassDto> list = new ArrayList<FlightClassDto>( flightClassEntities.size() );
        for ( FlightClassEntity flightClassEntity : flightClassEntities ) {
            list.add( toDto( flightClassEntity ) );
        }

        return list;
    }

    @Override
    public List<FlightClassEntity> toEntityList(List<FlightClassDto> flightClassDtoList) {
        if ( flightClassDtoList == null ) {
            return null;
        }

        List<FlightClassEntity> list = new ArrayList<FlightClassEntity>( flightClassDtoList.size() );
        for ( FlightClassDto flightClassDto : flightClassDtoList ) {
            list.add( toEntity( flightClassDto ) );
        }

        return list;
    }

    protected ClassEntity flightClassDtoToClassEntity(FlightClassDto flightClassDto) {
        if ( flightClassDto == null ) {
            return null;
        }

        ClassEntity classEntity = new ClassEntity();

        classEntity.setId( flightClassDto.getClassId() );

        return classEntity;
    }
}
