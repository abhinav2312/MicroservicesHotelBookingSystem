package com.ddsproject.hotelbooking.service;

import com.ddsproject.hotelbooking.dto.HotelRequest;
import com.ddsproject.hotelbooking.dto.HotelResponse;
import com.ddsproject.hotelbooking.model.Hotel;
import com.ddsproject.hotelbooking.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class HotelService {

    private final HotelRepository hotelRepository;

    public void createHotel(HotelRequest hotelRequest){
        Hotel hotel = Hotel.builder()
                .name(hotelRequest.getName())
                .description(hotelRequest.getDescription())
                .price(hotelRequest.getPrice())
                .build();

        hotelRepository.save(hotel);
        log.info("Hotel {} is saved",hotel.getId());
    }

    public List<HotelResponse> getAllHotels() {
        List<Hotel> hotels=hotelRepository.findAll();

        return hotels.stream().map(this::mapToProductResponse).toList();
    }

    private HotelResponse mapToProductResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .description(hotel.getDescription())
                .price(hotel.getPrice())
                .build();
    }
}
