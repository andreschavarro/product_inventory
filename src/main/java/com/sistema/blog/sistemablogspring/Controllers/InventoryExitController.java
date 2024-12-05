package com.sistema.blog.sistemablogspring.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistema.blog.sistemablogspring.DTOS.InventoryExitDTO;
import com.sistema.blog.sistemablogspring.Services.InventoryExitService;

@RestController
@RequestMapping("/api/inventory/exits")
public class InventoryExitController {

    private final InventoryExitService exitService;

    public InventoryExitController(InventoryExitService exitService) {
        this.exitService = exitService;
    }

    @PostMapping
    public ResponseEntity<InventoryExitDTO> createExit(@RequestBody InventoryExitDTO exitDTO) {
        InventoryExitDTO createdExit = exitService.createInventoryExit(exitDTO);
        return ResponseEntity.ok(createdExit);
    }
}