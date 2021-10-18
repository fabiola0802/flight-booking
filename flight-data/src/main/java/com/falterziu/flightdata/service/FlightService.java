package com.falterziu.flightdata.service;

import com.falterziu.flightdata.dto.flight.FlightDto;
import com.falterziu.flightdata.dto.flight.FlightFilter;
import com.falterziu.flightdata.dto.flight.FlightResponseDto;
import org.springframework.data.domain.Page;

public interface FlightService {

    FlightResponseDto createFlight(FlightDto flight);
    FlightResponseDto updateFlight(Integer id,FlightDto flight);
    Page<FlightResponseDto> getAll(Integer pageNumber, Integer pageSize, FlightFilter flightFilter);
    void deleteFlight(Integer id);
    FlightResponseDto getFlight(Integer id);
}
