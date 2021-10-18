package com.falterziu.flightdata.service.impl;

import com.falterziu.flightdata.dto.classes.ClassDto;
import com.falterziu.flightdata.entity.ClassEntity;
import com.falterziu.flightdata.mapper.ClassMapper;
import com.falterziu.flightdata.repository.ClassRepository;
import com.falterziu.flightdata.service.ClassService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
@Transactional
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final ClassMapper classMapper;

    @Override
    public List<ClassDto> getAllClasses() {
        return classMapper.toDtoList(classRepository.findAll());
    }

    @Override
    public List<ClassEntity> getAllClassesWithIds(List<Integer> classesIds) {
        log.info("Retrieving all classes from the database..");
        return classRepository.findClassEntityByIds(classesIds);
    }


}
