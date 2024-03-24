package com.ddsproject.bookingsservice.controller;

import com.ddsproject.bookingsservice.dto.BookingsRequest;
import com.ddsproject.bookingsservice.service.BookingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/bookings")
@RequiredArgsConstructor
public class BookingsController {

    private final BookingsService bookingsService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String makeBooking(@RequestBody BookingsRequest bookingsRequest){
        bookingsService.makeBooking(bookingsRequest);
        return "Booking made successfully";
    }
}
