package com.ddsproject.bookingsservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingsPlacedEvent {
    private String bookingNumber;
}
