package com.ddsproject.bookingsservice.repository;
import com.ddsproject.bookingsservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingsRepository extends JpaRepository<Booking, Long> {
}
