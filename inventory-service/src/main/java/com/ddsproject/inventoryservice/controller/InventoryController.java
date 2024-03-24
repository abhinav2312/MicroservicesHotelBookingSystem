package com.ddsproject.inventoryservice.controller;

import com.ddsproject.inventoryservice.dto.InventoryResponse;
import com.ddsproject.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor

public class InventoryController {

    private final InventoryService inventoryService;

    // http://localhost:8082/api/inventory/suraj,amber_dale

    // http://localhost:8082/api/inventory?skuCode=suraj&skuCode=amber_dale

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isAvailable(@RequestParam List<String> skuCode){
        return inventoryService.isAvailable(skuCode);
    }

}
