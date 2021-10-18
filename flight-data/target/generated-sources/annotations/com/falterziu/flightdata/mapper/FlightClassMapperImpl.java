package com.falterziu.flightdata.mapper;

import com.falterziu.flightdata.dto.classes.ClassDto;
import com.falterziu.flightdata.dto.flight_class.FlightClassDto;
import com.falterziu.flightdata.dto.flight_class.FlightClassResponseDto;
import com.falterziu.flightdata.entity.ClassEntity;
import com.falterziu.flightdata.entity.FlightClassEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-18T00:31:19+0200",
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
        flightClassEntity.setId( flightClassDto.getId() );
        flightClassEntity.setPrice( flightClassDto.getPrice() );
        flightClassEntity.setCapacity( flightClassDto.getCapacity() );

        return flightClassEntity;
    }

    @Override
    public FlightClassResponseDto toDto(FlightClassEntity flightClassEntity) {
        if ( flightClassEntity == null ) {
            return null;
        }

        FlightClassResponseDto flightClassResponseDto = new FlightClassResponseDto();

        flightClassResponseDto.setClassDto( classEntityToClassDto( flightClassEntity.getClassEntity() ) );
        flightClassResponseDto.setId( flightClassEntity.getId() );
        flightClassResponseDto.setPrice( flightClassEntity.getPrice() );
        flightClassResponseDto.setCapacity( flightClassEntity.getCapacity() );

        return flightClassResponseDto;
    }

    @Override
    public List<FlightClassResponseDto> toDtoList(List<FlightClassEntity> flightClassEntities) {
        if ( flightClassEntities == null ) {
            return null;
        }

        List<FlightClassResponseDto> list = new ArrayList<FlightClassResponseDto>( flightClassEntities.size() );
        for ( FlightClassEntity flightClassEntity : flightClassEntities ) {
            list.add( toDto( flightClassEntity ) );
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

    protected ClassDto classEntityToClassDto(ClassEntity classEntity) {
        if ( classEntity == null ) {
            return null;
        }

        ClassDto classDto = new ClassDto();

        classDto.setId( classEntity.getId() );
        classDto.setName( classEntity.getName() );

        return classDto;
    }
}
