package com.ddsproject.inventoryservice.service;


import com.ddsproject.inventoryservice.dto.InventoryResponse;
import com.ddsproject.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class InventoryService {

    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    public List<InventoryResponse> isAvailable(List<String> skuCode){

        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map( inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isAvailable(inventory.getRooms() > 0 )
                            .build()
                ).toList();

    }
}
