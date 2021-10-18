package com.falterziu.flightdata.repository;

import com.falterziu.flightdata.entity.FlightEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class FlightSpecifications {

    public static Specification<FlightEntity> departureTimeGreaterThan(LocalDate from){
        if(from != null){
            return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("departureTime"), from.atStartOfDay());
        }
        return null;
    }

    public static Specification<FlightEntity> arrivalTimeLessThan(LocalDate to){
        if(to != null){
            return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("departureTime"), to.atStartOfDay());
        }
        return null;
    }
}
