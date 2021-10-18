package com.falterziu.flightdata.repository;

import com.falterziu.flightdata.entity.BookingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {

    Optional<BookingEntity> getBookingEntityById(Integer id);


    @Query(value = "select *  from  booking where booking.status = ?1", nativeQuery = true)
    Page<BookingEntity> findAllReservedBookings(Pageable pageable, String status);

    @Query("select b from #{#entityName}  b where b.user.id = ?1")
    Page<BookingEntity> findAllBookingsOfUser(Pageable pageable,Integer userId);

    @Query(value = "select count(*) from booking where EXTRACT(YEAR FROM booking.booking_date) = ?1 AND booking.customer_id = ?2 AND booking.validity=true", nativeQuery = true)
    Integer countBookingsOfUserThisYear(Integer  year, Integer userId);
}
