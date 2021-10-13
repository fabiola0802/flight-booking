package com.falterziu.flightweb.controller;


import com.falterziu.flightdata.dto.flight.FlightDto;
import com.falterziu.flightdata.dto.flight.FlightResponseDto;
import com.falterziu.flightdata.service.FlightService;
import com.falterziu.flightdata.util.Routes;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(Routes.FLIGHTS)
public class FlightController {

private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<FlightResponseDto> addFlight(@Valid @RequestBody FlightDto flight) {
        return ResponseEntity.ok(flightService.createFlight(flight));
    }

    @GetMapping
    public ResponseEntity<Page<FlightResponseDto>> getAll(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        return ResponseEntity.ok(flightService.getAll(pageNumber,pageSize));
    }
}
