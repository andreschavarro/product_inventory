package com.sistema.blog.sistemablogspring.Mappers;

import com.sistema.blog.sistemablogspring.DTOS.InventoryEntryDTO;
import com.sistema.blog.sistemablogspring.Entities.InventoryEntry;

public class InventoryEntryMapper {

    public static InventoryEntryDTO toDTO(InventoryEntry entry) {
        if (entry == null) return null;

        InventoryEntryDTO dto = new InventoryEntryDTO();
        dto.setId(entry.getId());
        dto.setProductId(entry.getProduct().getId());
        dto.setQuantity(entry.getQuantity());
        dto.setExpirationDate(entry.getExpirationDate());
        return dto;
    }

    public static InventoryEntry toEntity(InventoryEntryDTO dto) {
        if (dto == null) return null;

        InventoryEntry entry = new InventoryEntry();
        entry.setQuantity(dto.getQuantity());
        entry.setExpirationDate(dto.getExpirationDate());
        return entry;
    }
}