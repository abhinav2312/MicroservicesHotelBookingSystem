package com.ddsproject.inventoryservice.service;


import com.ddsproject.inventoryservice.dto.InventoryResponse;
import com.ddsproject.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isAvailable(List<String> skuCode){
        log.info("Wait Started");
//        Thread.sleep(10000);
        log.info("Wait Ended");
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map( inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isAvailable(inventory.getRooms() > 0 )
                            .build()
                ).toList();

    }
}
