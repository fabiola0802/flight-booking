package com.falterziu.flightdata.mapper;

import com.falterziu.flightdata.dto.classes.ClassDto;
import com.falterziu.flightdata.entity.ClassEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassMapper {

    List<ClassDto> toDtoList (List<ClassEntity> classEntities);

}
