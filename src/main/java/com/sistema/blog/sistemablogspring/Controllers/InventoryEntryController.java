package com.sistema.blog.sistemablogspring.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistema.blog.sistemablogspring.DTOS.InventoryEntryDTO;
import com.sistema.blog.sistemablogspring.Services.InventoryEntryService;

@RestController
@RequestMapping("/api/inventory/entries")
public class InventoryEntryController {

    private final InventoryEntryService entryService;

    public InventoryEntryController(InventoryEntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping
    public ResponseEntity<InventoryEntryDTO> createEntry(@RequestBody InventoryEntryDTO entryDTO) {
        InventoryEntryDTO createdEntry = entryService.createInventoryEntry(entryDTO);
        return ResponseEntity.ok(createdEntry);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<InventoryEntryDTO>> getEntriesByProductId(@PathVariable Long productId) {
        List<InventoryEntryDTO> entries = entryService.getEntriesByProductId(productId);
        return ResponseEntity.ok(entries);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        entryService.deleteEntry(id);
        return ResponseEntity.noContent().build();
    }
}