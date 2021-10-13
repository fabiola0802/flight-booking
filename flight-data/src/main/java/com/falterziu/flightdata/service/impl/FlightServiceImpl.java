package com.falterziu.flightdata.service.impl;

import com.falterziu.flightdata.dto.flight.FlightDto;
import com.falterziu.flightdata.dto.flight.FlightResponseDto;
import com.falterziu.flightdata.dto.flight_class.FlightClassDto;
import com.falterziu.flightdata.entity.FlightClassEntity;
import com.falterziu.flightdata.entity.FlightEntity;
import com.falterziu.flightdata.exception.FlightAppBadRequestException;
import com.falterziu.flightdata.exception.FlightAppNotFoundException;
import com.falterziu.flightdata.mapper.FlightClassMapper;
import com.falterziu.flightdata.mapper.FlightMapper;
import com.falterziu.flightdata.repository.ClassRepository;
import com.falterziu.flightdata.repository.FlightRepository;
import com.falterziu.flightdata.service.ClassService;
import com.falterziu.flightdata.service.FlightService;
import com.falterziu.flightdata.util.BadRequest;
import com.falterziu.flightdata.util.NotFound;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final FlightClassMapper flightClassMapper;
    private final ClassService classService;
    private final ClassRepository classRepository;


    @Override
    public FlightResponseDto createFlight(FlightDto flight) {
        validatingDates(flight);
        validateClasses(flight);
        validateCapacities(flight);
        FlightEntity flightEntity = flightMapper.toEntity(flight);
        flightEntity.setValidity(Boolean.TRUE);
        if(flight.getFlightClasses()!=null){
             flight.getFlightClasses().forEach(flightClassDto-> {
                 FlightClassEntity flightClass = flightClassMapper.toEntity(flightClassDto);
                 flightClass.setClassEntity(classRepository.findById(flightClassDto.getClassId()).get());
                 flightEntity.addFlightClass(flightClass);
             });
         }
        return flightMapper.toDto(flightRepository.save(flightEntity));
    }



    @Override
    public FlightResponseDto updateFlight(Integer id, FlightDto flight) {
        //mos lejo te editohet nese ka nje booking per kete fluturim
        FlightEntity flightEntity = Optional.of(flightRepository.getById(id)).
                orElseThrow(() -> new FlightAppNotFoundException(NotFound.FLIGHT_NOT_FOUND));
        validatingDates(flight);
        validateClasses(flight);
        validateCapacities(flight);
        if(flight.getFlightClasses()!=null){
            flight.getFlightClasses().forEach(flightClassDto-> {
                FlightClassEntity flightClass = flightClassMapper.toEntity(flightClassDto);
                flightClass.setClassEntity(classRepository.findById(flightClassDto.getClassId()).get());
                flightEntity.addFlightClass(flightClass);
            });
        }
        flightEntity.setCapacity(flight.getCapacity());
        flightEntity.setArrivalTime(flight.getArrivalTime());
        flightEntity.setDepartureTime(flight.getDepartureTime());
        flightEntity.setDeparture(flight.getDeparture());
        flightEntity.setDestination(flight.getDestination());

        return flightMapper.toDto(flightRepository.save(flightEntity));
    }

    @Override
    public Page<FlightResponseDto> getAll(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.DESC, "departureTime"));
        return flightMapper.toPageDto(flightRepository.findAll(pageable));
    }

    @Override
    public void deleteFlight(Integer id) {
        //mos lejo te anulohet nese ka nje booking per kete fluturim // ose mund te lejohet
        FlightEntity flight = Optional.of(flightRepository.getById(id)).
                orElseThrow(() -> new FlightAppNotFoundException(NotFound.FLIGHT_NOT_FOUND));
        flightRepository.delete(flight);
    }


    @Override
    public FlightResponseDto getFlight(Integer id) {
        FlightEntity flight = Optional.of(flightRepository.getById(id)).
                orElseThrow(() -> new FlightAppNotFoundException(NotFound.FLIGHT_NOT_FOUND));
        return  flightMapper.toDto(flight);
    }


    private void validatingDates(FlightDto flight) {
        if (flight.getArrivalTime().isBefore(flight.getDepartureTime())
                || flight.getDepartureTime().isBefore(LocalDateTime.now())
                || flight.getArrivalTime().isBefore(LocalDateTime.now())){
            throw new FlightAppBadRequestException(BadRequest.WRONG_DATES);
        }
    }


    private void validateClasses(FlightDto flight) {
        if(flight.getFlightClasses()!= null){
            List<Integer> classesIds = flight.getFlightClasses().stream().map(FlightClassDto::getClassId).collect(Collectors.toList());
            if(classesIds.size() != classesIds.stream().distinct().count()){
                throw new FlightAppBadRequestException(BadRequest.DUPLICATED_CLASSES);
            }
            classService.getAllClassesWithIds(classesIds);
        }
    }


    private void validateCapacities(FlightDto flight){
        if(flight.getCapacity() < flight.getFlightClasses().stream().mapToInt(FlightClassDto::getCapacity).sum())
            throw new FlightAppBadRequestException(BadRequest.WRONG_CAPACITIES);
    }

}
