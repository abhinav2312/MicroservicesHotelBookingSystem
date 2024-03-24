package com.ddsproject.bookingsservice.service;

import com.ddsproject.bookingsservice.dto.BookingLineItemsDto;
import com.ddsproject.bookingsservice.dto.BookingsRequest;
import com.ddsproject.bookingsservice.dto.InventoryResponse;
import com.ddsproject.bookingsservice.model.Booking;
import com.ddsproject.bookingsservice.model.BookingLineItems;
import com.ddsproject.bookingsservice.repository.BookingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingsService {

    private final BookingsRepository bookingsRepository;
    private final WebClient.Builder webClientBuilder;

    public String makeBooking(BookingsRequest bookingsRequest){
        Booking booking=new Booking();
        booking.setBookingNumber(UUID.randomUUID().toString());

        List<BookingLineItems> bookingLineItems= bookingsRequest.getBookingLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        booking.setBookingLineItemsList(bookingLineItems);


        List<String> skuCodes = booking.getBookingLineItemsList().stream()
                .map(BookingLineItems::getSkuCode)
                .toList();




        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();


        boolean allRoomsAvailable = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isAvailable);



        if(allRoomsAvailable) {
            bookingsRepository.save(booking);
            return "Booking made Successfully";
        }
        else {
            throw new IllegalArgumentException(" Rooms are not available , Please Try Later");
        }
    }

    private BookingLineItems mapToDto(BookingLineItemsDto bookingLineItemsDto) {
        BookingLineItems bookingLineItems=new BookingLineItems();
        bookingLineItems.setPrice(bookingLineItemsDto.getPrice());
        bookingLineItems.setRooms(bookingLineItemsDto.getRooms());
        bookingLineItems.setSkuCode(bookingLineItemsDto.getSkuCode());

        return bookingLineItems;
    }
}
