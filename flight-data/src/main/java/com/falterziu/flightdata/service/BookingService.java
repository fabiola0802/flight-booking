package com.falterziu.flightdata.service;

import com.falterziu.flightdata.dto.booking.BookingDto;
import com.falterziu.flightdata.dto.booking.BookingRejectDto;
import com.falterziu.flightdata.dto.booking.BookingResponseDto;
import com.falterziu.flightdata.dto.booking.SupervisorBookingDto;
import com.falterziu.flightdata.enumeration.Status;
import org.springframework.data.domain.Page;

public interface BookingService {

     BookingResponseDto createBooking(BookingDto bookingDto, String email) ;

     BookingResponseDto createBooking(SupervisorBookingDto supervisorBookingDto, String email);

     BookingResponseDto updateBooking(Integer id, String email ,BookingDto bookingDto) ;

     void deleteBooking(Integer id, String email) ;

     Page<BookingResponseDto> getAllReservedBookings(Integer pageNumber, Integer pageSize, Status status) ;

     Page<BookingResponseDto> getAllBookingsOfUser(Integer pageNumber, Integer pageSize, Integer userId) ;

     void acceptBooking(Status status, Integer bookingId);

     void rejectBooking(Status Status, Integer bookingId, BookingRejectDto bookingRejectDto);

     BookingResponseDto getBookingById(Integer id);

}
