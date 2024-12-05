package com.sistema.blog.sistemablogspring.Mappers;

import com.sistema.blog.sistemablogspring.DTOS.InventoryExitDTO;
import com.sistema.blog.sistemablogspring.Entities.InventoryExit;

public class InventoryExitMapper {

    public static InventoryExitDTO toDTO(InventoryExit exit) {
        if (exit == null) return null;

        InventoryExitDTO dto = new InventoryExitDTO();
        dto.setId(exit.getId());
        dto.setEntryId(exit.getEntry().getId());
        dto.setQuantity(exit.getQuantity());
        return dto;
    }

    public static InventoryExit toEntity(InventoryExitDTO dto) {
        if (dto == null) return null;

        InventoryExit exit = new InventoryExit();
        exit.setQuantity(dto.getQuantity());
        return exit;
    }
}
