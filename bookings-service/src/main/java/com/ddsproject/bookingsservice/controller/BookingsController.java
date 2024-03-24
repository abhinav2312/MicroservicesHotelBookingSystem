package com.ddsproject.bookingsservice.controller;

import com.ddsproject.bookingsservice.dto.BookingsRequest;
import com.ddsproject.bookingsservice.service.BookingsService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/bookings")
@RequiredArgsConstructor
public class BookingsController {

    private final BookingsService bookingsService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> makeBooking(@RequestBody BookingsRequest bookingsRequest){
        return CompletableFuture.supplyAsync(()->bookingsService.makeBooking(bookingsRequest));
    }

    public CompletableFuture<String> fallbackMethod(BookingsRequest bookingsRequest, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->"Oops! Something went wrong, please order after sometime!");
    }
}
