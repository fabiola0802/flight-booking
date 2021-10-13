package com.falterziu.flightdata.repository;

import com.falterziu.flightdata.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
}
