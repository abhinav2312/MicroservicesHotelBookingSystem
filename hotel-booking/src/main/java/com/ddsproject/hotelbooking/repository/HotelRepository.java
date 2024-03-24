package com.ddsproject.hotelbooking.repository;


import com.ddsproject.hotelbooking.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelRepository extends MongoRepository<Hotel, String> {
}
