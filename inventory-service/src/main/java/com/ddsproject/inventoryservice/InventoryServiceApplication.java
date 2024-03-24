package com.ddsproject.inventoryservice;

import com.ddsproject.inventoryservice.model.Inventory;
import com.ddsproject.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


@EnableDiscoveryClient
@SpringBootApplication

public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("skanda");
			inventory.setRooms(5);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("garuda");
			inventory1.setRooms(10);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};

	}

}
