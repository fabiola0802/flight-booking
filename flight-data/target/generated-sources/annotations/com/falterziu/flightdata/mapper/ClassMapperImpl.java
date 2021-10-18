package com.falterziu.flightdata.mapper;

import com.falterziu.flightdata.dto.classes.ClassDto;
import com.falterziu.flightdata.entity.ClassEntity;
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
public class ClassMapperImpl implements ClassMapper {

    @Override
    public List<ClassDto> toDtoList(List<ClassEntity> classEntities) {
        if ( classEntities == null ) {
            return null;
        }

        List<ClassDto> list = new ArrayList<ClassDto>( classEntities.size() );
        for ( ClassEntity classEntity : classEntities ) {
            list.add( classEntityToClassDto( classEntity ) );
        }

        return list;
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
