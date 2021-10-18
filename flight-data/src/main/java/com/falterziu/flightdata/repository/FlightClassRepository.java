package com.falterziu.flightdata.repository;

import com.falterziu.flightdata.entity.FlightClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightClassRepository extends JpaRepository<FlightClassEntity, Integer> {

    Optional<FlightClassEntity> getFlightClassEntityById(Integer id);
}
