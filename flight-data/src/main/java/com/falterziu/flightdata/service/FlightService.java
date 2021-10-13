package com.falterziu.flightdata.service;

import com.falterziu.flightdata.dto.flight.FlightDto;
import com.falterziu.flightdata.dto.flight.FlightResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FlightService {

    FlightResponseDto createFlight(FlightDto flight);
    FlightResponseDto updateFlight(Integer id,FlightDto flight);
    Page<FlightResponseDto> getAll(Integer pageNumber, Integer pageSize);
    void deleteFlight(Integer id);
    FlightResponseDto getFlight(Integer id);

}
