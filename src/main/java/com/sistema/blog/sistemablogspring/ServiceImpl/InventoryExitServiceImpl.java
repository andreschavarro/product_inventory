package com.sistema.blog.sistemablogspring.ServiceImpl;

import org.springframework.stereotype.Service;

import com.sistema.blog.sistemablogspring.DTOS.InventoryExitDTO;
import com.sistema.blog.sistemablogspring.Entities.InventoryEntry;
import com.sistema.blog.sistemablogspring.Entities.InventoryExit;
import com.sistema.blog.sistemablogspring.Mappers.InventoryExitMapper;
import com.sistema.blog.sistemablogspring.Repositories.InventoryEntryRepository;
import com.sistema.blog.sistemablogspring.Repositories.InventoryExitRepository;
import com.sistema.blog.sistemablogspring.Services.InventoryExitService;

@Service
public class InventoryExitServiceImpl implements InventoryExitService {

    private final InventoryExitRepository exitRepository;
    private final InventoryEntryRepository entryRepository;

    public InventoryExitServiceImpl(InventoryExitRepository exitRepository, InventoryEntryRepository entryRepository) {
        this.exitRepository = exitRepository;
        this.entryRepository = entryRepository;
    }

    @Override
    public InventoryExitDTO createInventoryExit(InventoryExitDTO exitDTO) {
        InventoryEntry entry = entryRepository.findById(exitDTO.getEntryId())
                .orElseThrow(() -> new IllegalArgumentException("Inventory entry not found"));

        if (exitDTO.getQuantity() > entry.getQuantity()) {
            throw new IllegalArgumentException("Not enough quantity in stock");
        }

        entry.setQuantity(entry.getQuantity() - exitDTO.getQuantity());
        entryRepository.save(entry);

        InventoryExit exit = InventoryExitMapper.toEntity(exitDTO);
        exit.setEntry(entry);
        exit = exitRepository.save(exit);

        return InventoryExitMapper.toDTO(exit);
    }
}