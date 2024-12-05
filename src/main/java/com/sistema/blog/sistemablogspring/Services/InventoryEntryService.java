package com.sistema.blog.sistemablogspring.Services;

import java.util.List;

import com.sistema.blog.sistemablogspring.DTOS.InventoryEntryDTO;

public interface InventoryEntryService {
    InventoryEntryDTO createInventoryEntry(InventoryEntryDTO entryDTO); 
    List<InventoryEntryDTO> getEntriesByProductId(Long productId); 
    void deleteEntry(Long id); 
}