package com.falterziu.flightdata.repository;

import com.falterziu.flightdata.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FlightRepository extends JpaRepository<FlightEntity, Integer> {

    @Modifying
    @Query("update #{#entityName} u set u.validity=false where u.id = ?1")
    void delete(Integer id);
}
