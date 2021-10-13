package com.falterziu.flightdata.repository;

import com.falterziu.flightdata.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {


    @Query("select c from #{#entityName}  c where c.id IN :ids ")
    List<ClassEntity> findClassEntityByIds(List<Integer> ids);

}
