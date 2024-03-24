package com.ddsproject.hotelbooking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ddsproject.hotelbooking.dto.HotelRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class HotelBookingApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
    @Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;


	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
	@Test
	void shouldCreateHotel() throws Exception {
		HotelRequest hotelRequest = getHotelRequest();
		String hotelRequestString = objectMapper.writeValueAsString(hotelRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/hotel")
					.contentType(MediaType.APPLICATION_JSON)
					.content(hotelRequestString))
				.andExpect(status().isCreated());

	}

	private HotelRequest getHotelRequest(){
		return HotelRequest.builder()
				.name("Garuda")
				.description("Beach view")
				.price(BigDecimal.valueOf(4000))
				.build();
	}


}
