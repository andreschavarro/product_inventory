package com.sistema.blog.sistemablogspring.ServiceImpl;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.sistema.blog.sistemablogspring.DTOS.InventoryEntryDTO;
import com.sistema.blog.sistemablogspring.Entities.InventoryEntry;
import com.sistema.blog.sistemablogspring.Mappers.InventoryEntryMapper;
import com.sistema.blog.sistemablogspring.Repositories.InventoryEntryRepository;
import com.sistema.blog.sistemablogspring.Repositories.ProductRepository;
import com.sistema.blog.sistemablogspring.Services.InventoryEntryService;

@Service
public class InventoryEntryServiceImpl implements InventoryEntryService {

	private final InventoryEntryRepository entryRepository;
	private final ProductRepository productRepository;

	public InventoryEntryServiceImpl(InventoryEntryRepository entryRepository, ProductRepository productRepository) {
		this.entryRepository = entryRepository;
		this.productRepository = productRepository;
	}

	@Override
	public InventoryEntryDTO createInventoryEntry(InventoryEntryDTO entryDTO) {
		if (entryDTO.getExpirationDate().isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Expiration date cannot be in the past");
		}
		InventoryEntry entry = InventoryEntryMapper.toEntity(entryDTO);
		entry.setProduct(productRepository.findById(entryDTO.getProductId())
				.orElseThrow(() -> new IllegalArgumentException("Product not found")));
		entry = entryRepository.save(entry);
		return InventoryEntryMapper.toDTO(entry);
	}

	@Override
	public List<InventoryEntryDTO> getEntriesByProductId(Long productId) {
		return entryRepository.findByProductId(productId).stream().map(InventoryEntryMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteEntry(Long id) {
		if (!entryRepository.existsById(id)) {
			throw new IllegalArgumentException("Inventory entry not found");
		}
		entryRepository.deleteById(id);
	}
}