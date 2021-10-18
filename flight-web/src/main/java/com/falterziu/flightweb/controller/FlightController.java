package com.falterziu.flightweb.controller;


import com.falterziu.flightdata.dto.flight.FlightDto;
import com.falterziu.flightdata.dto.flight.FlightFilter;
import com.falterziu.flightdata.dto.flight.FlightResponseDto;
import com.falterziu.flightdata.service.FlightService;
import com.falterziu.flightdata.util.Routes;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('SUPERVISOR')")
    public ResponseEntity<FlightResponseDto> addFlight(@Valid @RequestBody FlightDto flight) {
        return ResponseEntity.ok(flightService.createFlight(flight));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SUPERVISOR', 'USER')")
    public ResponseEntity<Page<FlightResponseDto>> getAll(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                          @RequestParam(value = "from")  String from,
                                                          @RequestParam(value = "to")  String to) {
        FlightFilter flightFilter = new FlightFilter(from,to);
        return ResponseEntity.ok(flightService.getAll(pageNumber,pageSize, flightFilter));
    }

    @PutMapping(Routes.BY_ID)
    @PreAuthorize("hasAuthority('SUPERVISOR')")
    public ResponseEntity<FlightResponseDto> updateFlight(@PathVariable Integer id, @Valid @RequestBody FlightDto flightDto){
        return ResponseEntity.ok(flightService.updateFlight(id,flightDto));
    }


    @DeleteMapping (Routes.BY_ID)
    @PreAuthorize("hasAuthority('SUPERVISOR')")
    public ResponseEntity<Void> deleteFlight(@PathVariable Integer id) {
        flightService.deleteFlight(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(Routes.BY_ID)
    @PreAuthorize("hasAnyAuthority('SUPERVISOR', 'USER')")
    public ResponseEntity<FlightResponseDto> getFlightById(@PathVariable Integer id){
        return ResponseEntity.ok(flightService.getFlight(id));
    }

}
