package com.falterziu.flightdata.repository;

import com.falterziu.flightdata.entity.FlightClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightClassRepository extends JpaRepository<FlightClassEntity, Integer> {
}
