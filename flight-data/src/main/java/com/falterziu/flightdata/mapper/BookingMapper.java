package com.falterziu.flightdata.mapper;

import com.falterziu.flightdata.dto.booking.BookingDto;
import com.falterziu.flightdata.dto.booking.BookingResponseDto;
import com.falterziu.flightdata.entity.BookingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", uses = FlightClassMapper.class)
public interface BookingMapper {

    @Mapping(target="flightClassDto",  source="flightClassEntity")
    BookingResponseDto toDto (BookingEntity bookingEntity);

    @Mapping(target="flightClassEntity.id",  source="flightClassId")
    BookingEntity toEntity(BookingDto bookingDto);

    default Page<BookingResponseDto> toPageDto (Page<BookingEntity> bookingEntityPage){
        return  bookingEntityPage.map(this::toDto);
    }
}
