package com.ddsproject.bookingsservice.dto;

import com.ddsproject.bookingsservice.model.BookingLineItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingsRequest {
    private List<BookingLineItemsDto> bookingLineItemsDtoList;
}
