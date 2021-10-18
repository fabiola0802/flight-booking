package com.falterziu.flightweb.controller;

import com.falterziu.flightdata.dto.booking.BookingDto;
import com.falterziu.flightdata.dto.booking.BookingRejectDto;
import com.falterziu.flightdata.dto.booking.BookingResponseDto;
import com.falterziu.flightdata.dto.booking.SupervisorBookingDto;
import com.falterziu.flightdata.enumeration.Status;
import com.falterziu.flightdata.service.BookingService;
import com.falterziu.flightdata.util.AuthenticationFacade;
import com.falterziu.flightdata.util.Routes;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = Routes.BOOKINGS)
public class BookingController {

    private final BookingService bookingService;
    private final AuthenticationFacade authenticationFacade;

    public BookingController(BookingService bookingService, AuthenticationFacade authenticationFacade) {
        this.bookingService = bookingService;
        this.authenticationFacade = authenticationFacade;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('SUPERVISOR', 'USER')")
    public ResponseEntity<BookingResponseDto> addBooking(@Valid @RequestBody BookingDto bookingDto) {
        String email  = authenticationFacade.getUserPrincipal().getEmail();
        return ResponseEntity.ok(bookingService.createBooking(bookingDto, email));
    }
    @PostMapping(Routes.USER)
    @PreAuthorize("hasAnyAuthority('SUPERVISOR')")
    public ResponseEntity<BookingResponseDto> bookForUser(@Valid @RequestBody SupervisorBookingDto supervisorBookingDto) {
        String email  = authenticationFacade.getUserPrincipal().getEmail();
        return ResponseEntity.ok(bookingService.createBooking(supervisorBookingDto, email));
    }

    @PutMapping(Routes.BY_ID)
    @PreAuthorize("hasAnyAuthority('SUPERVISOR', 'USER')")
    public ResponseEntity<BookingResponseDto> updateBooking(@PathVariable Integer id, @RequestBody BookingDto bookingDto){
        String email  = authenticationFacade.getUserPrincipal().getEmail();
        return  ResponseEntity.ok(bookingService.updateBooking(id, email, bookingDto));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SUPERVISOR')")
    public ResponseEntity<Page<BookingResponseDto>> getAll(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        return ResponseEntity.ok(bookingService.getAllReservedBookings(pageNumber,pageSize,Status.RESERVED));
    }


    @GetMapping(Routes.USER_BOOKINGS)
    @PreAuthorize("hasAnyAuthority('SUPERVISOR', 'USER')")
    public ResponseEntity<Page<BookingResponseDto>> getAllBookingsOfUser(@PathVariable Integer id,@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        return ResponseEntity.ok(bookingService.getAllBookingsOfUser(pageNumber,pageSize, id));
    }


    @DeleteMapping (Routes.BY_ID)
    @PreAuthorize("hasAnyAuthority('SUPERVISOR', 'USER')")
    public ResponseEntity<Void> deleteBooking(@PathVariable Integer id) {
        String email  = authenticationFacade.getUserPrincipal().getEmail();
        bookingService.deleteBooking(id, email);
        return ResponseEntity.ok().build();
    }

    @GetMapping(Routes.BY_ID)
    @PreAuthorize("hasAnyAuthority('SUPERVISOR', 'USER')")
    public ResponseEntity<BookingResponseDto> getBookingById(@PathVariable Integer id){
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }



   @PutMapping(Routes.APPROVAL)
   @PreAuthorize("hasAnyAuthority('SUPERVISOR')")
   public ResponseEntity<Void> approveBooking(@PathVariable Integer id){
       bookingService.acceptBooking(Status.APPROVED , id);
       return  ResponseEntity.ok().build();
   }

    @PutMapping(Routes.REJECTION)
    @PreAuthorize("hasAnyAuthority('SUPERVISOR')")
    public ResponseEntity<Void> rejectBooking(@PathVariable Integer id, @Valid @RequestBody BookingRejectDto bookingRejectDto){
        bookingService.rejectBooking(Status.REJECTED , id, bookingRejectDto);
        return  ResponseEntity.ok().build();
    }
}
