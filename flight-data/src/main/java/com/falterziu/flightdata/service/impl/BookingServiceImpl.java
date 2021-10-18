package com.falterziu.flightdata.service.impl;

import com.falterziu.flightdata.dto.booking.BookingDto;
import com.falterziu.flightdata.dto.booking.BookingRejectDto;
import com.falterziu.flightdata.dto.booking.BookingResponseDto;
import com.falterziu.flightdata.dto.booking.SupervisorBookingDto;
import com.falterziu.flightdata.entity.BookingEntity;
import com.falterziu.flightdata.entity.FlightClassEntity;
import com.falterziu.flightdata.entity.NotificationEntity;
import com.falterziu.flightdata.entity.UserEntity;
import com.falterziu.flightdata.enumeration.Status;
import com.falterziu.flightdata.enumeration.Type;
import com.falterziu.flightdata.exception.FlightAppBadRequestException;
import com.falterziu.flightdata.exception.FlightAppNotFoundException;
import com.falterziu.flightdata.mapper.BookingMapper;
import com.falterziu.flightdata.repository.BookingRepository;
import com.falterziu.flightdata.repository.FlightClassRepository;
import com.falterziu.flightdata.repository.NotificationRepository;
import com.falterziu.flightdata.repository.UserRepository;
import com.falterziu.flightdata.service.BookingService;
import com.falterziu.flightdata.util.BadRequest;
import com.falterziu.flightdata.util.Constant;
import com.falterziu.flightdata.util.NotFound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UserRepository userRepository;
    private final FlightClassRepository flightClassRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public BookingResponseDto createBooking(BookingDto bookingDto, String email) {
        log.debug("Checking if user exists {}", email);
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() ->
                new FlightAppNotFoundException(NotFound.USER_NOT_FOUND));
        log.debug("Checking if  flight class exists with id {}", bookingDto.getFlightClassId());
        FlightClassEntity flightClass = flightClassRepository.getFlightClassEntityById(bookingDto.getFlightClassId()).orElseThrow(()
                -> new FlightAppNotFoundException(NotFound.FLIGHT_NOT_FOUND));
        if(bookingRepository.countBookingsOfUserThisYear(LocalDate.now().getYear(), user.getId()) >= Constant.maxFlightsPerYear ){
            throw new FlightAppBadRequestException(BadRequest.MAX_FLIGHTS_EXCEEDED);
        }
        BookingEntity booking = new BookingEntity();
        booking.setBookingDate(LocalDate.now());
        booking.setStatus(Status.RESERVED);
        booking.setUser(user);
        booking.setValidity(Boolean.TRUE);
        booking.setFlightClassEntity(flightClass);
        log.debug("Saving booking {} " , booking);
        return bookingMapper.toDto(bookingRepository.save(booking));
    }

    @Override
    public BookingResponseDto createBooking(SupervisorBookingDto supervisorBookingDto, String email) {
        log.debug("Checking if user  {}", supervisorBookingDto.getUserId());
        UserEntity user = userRepository.findById(supervisorBookingDto.getUserId()).orElseThrow(() ->
                new FlightAppNotFoundException(NotFound.USER_NOT_FOUND));
        FlightClassEntity flightClass = flightClassRepository.getFlightClassEntityById(supervisorBookingDto.getFlightClassId()).orElseThrow(()
                -> new FlightAppNotFoundException(NotFound.FLIGHT_NOT_FOUND));
        if(bookingRepository.countBookingsOfUserThisYear(LocalDate.now().getYear(), user.getId()) >= Constant.maxFlightsPerYear ){
            throw new FlightAppBadRequestException(BadRequest.MAX_FLIGHTS_EXCEEDED);
        }
        BookingEntity booking = new BookingEntity();
        booking.setBookingDate(LocalDate.now());
        booking.setStatus(Status.RESERVED);
        booking.setUser(user);
        booking.setValidity(Boolean.TRUE);
        booking.setFlightClassEntity(flightClass);
        log.debug("Saving booking {} " , booking);
        return bookingMapper.toDto(bookingRepository.save(booking));
    }

    @Override
    public BookingResponseDto updateBooking(Integer id, String email, BookingDto bookingDto) {
        log.debug("Checking if booking exists with id {}", id);
        BookingEntity booking = bookingRepository.getBookingEntityById(id).orElseThrow(() ->
                new FlightAppNotFoundException(NotFound.BOOKING_NOT_FOUND));
        if (!booking.getUser().getEmail().equals(email)) {
            throw new FlightAppBadRequestException(BadRequest.NOT_ALLOWED);
        }
        FlightClassEntity flightClass = flightClassRepository.getFlightClassEntityById(bookingDto.getFlightClassId()).orElseThrow(()
                -> new FlightAppNotFoundException(NotFound.FLIGHT_NOT_FOUND));
        booking.setFlightClassEntity(flightClass);
        booking.setBookingDate(LocalDate.now());
        return bookingMapper.toDto(bookingRepository.save(booking));
    }

    @Override
    public void deleteBooking(Integer id, String email) {
        BookingEntity booking = bookingRepository.getBookingEntityById(id).orElseThrow(() ->
                new FlightAppNotFoundException(NotFound.BOOKING_NOT_FOUND));
        if (!booking.getUser().getEmail().equals(email)) {
            throw new FlightAppBadRequestException(BadRequest.NOT_ALLOWED);
        }
        booking.setValidity(Boolean.FALSE);
        log.debug("Deleting booking with id {}", id);
        bookingRepository.save(booking);

    }

    @Override
    public Page<BookingResponseDto> getAllReservedBookings(Integer pageNumber, Integer pageSize, Status status) {
        log.info("Retrieving all bookings with status RESERVED and sorting them by booking date");
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.DESC, "booking_date"));
        return bookingMapper.toPageDto(bookingRepository.findAllReservedBookings(pageable,Status.RESERVED.toString()));
    }

    @Override
    public Page<BookingResponseDto> getAllBookingsOfUser(Integer pageNumber, Integer pageSize, Integer userId) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.DESC, "bookingDate"));
        log.debug("Retrieving all bookings of user with id {}", userId);
        return bookingMapper.toPageDto(bookingRepository.findAllBookingsOfUser(pageable, userId));
    }

    @Override
    public void acceptBooking(Status status, Integer bookingId) {
        BookingEntity booking = bookingRepository.getBookingEntityById(bookingId).orElseThrow(() ->
                new FlightAppNotFoundException(NotFound.BOOKING_NOT_FOUND));

        if (!booking.getStatus().toString().equals(Status.RESERVED.toString())) {
            throw new FlightAppBadRequestException(BadRequest.NOT_ALLOWED);
        }
            booking.setStatus(status);
            bookingRepository.save(booking);
            NotificationEntity notificationEntity = new NotificationEntity();
                notificationEntity.setType(Type.ACCEPTED);
                notificationEntity.setBooking(booking);
                notificationEntity.setCreatedOn(LocalDateTime.now());
                notificationEntity.setMessage(Constant.APPROVED_BOOKING);
                notificationRepository.save(notificationEntity);

    }

    @Override
    public void rejectBooking(Status status, Integer bookingId, BookingRejectDto bookingRejectDto) {
        BookingEntity booking = bookingRepository.getBookingEntityById(bookingId).orElseThrow(() ->
                new FlightAppNotFoundException(NotFound.BOOKING_NOT_FOUND));

        if (!booking.getStatus().equals(Status.RESERVED)) {
            throw new FlightAppBadRequestException(BadRequest.NOT_ALLOWED);
        }

            booking.setStatus(status);
            booking.setNote(bookingRejectDto.getNote());
            bookingRepository.save(booking);
            NotificationEntity notificationEntity = new NotificationEntity();
            notificationEntity.setType(Type.REJECTED);
            notificationEntity.setBooking(booking);
            notificationEntity.setCreatedOn(LocalDateTime.now());
            notificationEntity.setMessage(Constant.REJECTED_BOOKING);
            notificationRepository.save(notificationEntity);

        }

    @Override
    public BookingResponseDto getBookingById(Integer id) {
        BookingEntity booking = bookingRepository.findById(id).orElseThrow(()->
                new FlightAppNotFoundException(NotFound.BOOKING_NOT_FOUND));
        return bookingMapper.toDto(booking);
    }


}
