package com.falterziu.flightdata.service;

import com.falterziu.flightdata.dto.classes.ClassDto;
import com.falterziu.flightdata.entity.ClassEntity;

import java.util.List;

public interface ClassService {

    List<ClassDto> getAllClasses();
    List <ClassEntity> getAllClassesWithIds(List<Integer> classesIds);

}
