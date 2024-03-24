package com.ddsproject.hotelbooking.controller;

import com.ddsproject.hotelbooking.dto.HotelRequest;
import com.ddsproject.hotelbooking.dto.HotelResponse;
import com.ddsproject.hotelbooking.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/hotel")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public void createHotel(@RequestBody HotelRequest hotelRequest){
        hotelService.createHotel(hotelRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<HotelResponse> getALlHotels(){
       return hotelService.getAllHotels();
    }


}
