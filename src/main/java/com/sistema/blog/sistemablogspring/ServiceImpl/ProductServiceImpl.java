package com.sistema.blog.sistemablogspring.ServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sistema.blog.sistemablogspring.DTOS.ProductDTO;
import com.sistema.blog.sistemablogspring.DTOS.ProductStateDTO;
import com.sistema.blog.sistemablogspring.Entities.InventoryEntry;
import com.sistema.blog.sistemablogspring.Entities.Product;
import com.sistema.blog.sistemablogspring.Mappers.ProductMapper;
import com.sistema.blog.sistemablogspring.Mappers.ProductStateMapper;
import com.sistema.blog.sistemablogspring.Repositories.InventoryEntryRepository;
import com.sistema.blog.sistemablogspring.Repositories.ProductRepository;
import com.sistema.blog.sistemablogspring.Services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final InventoryEntryRepository entryRepository;

    public ProductServiceImpl(ProductRepository productRepository, InventoryEntryRepository entryRepository) {
        this.productRepository = productRepository;
        this.entryRepository = entryRepository;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        if (productRepository.existsByName(productDTO.getName())) {
            throw new IllegalArgumentException("Product with name already exists");
        }
        Product product = ProductMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return ProductMapper.toDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id).map(ProductMapper::toDTO);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductStateDTO> getProductStates() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> {
            List<InventoryEntry> entries = entryRepository.findByProductId(product.getId());
            int totalQuantity = entries.stream().mapToInt(InventoryEntry::getQuantity).sum();
            String state = calculateProductState(entries);
            return ProductStateMapper.toDTO(product, totalQuantity, state);
        }).collect(Collectors.toList());
    }

    private String calculateProductState(List<InventoryEntry> entries) {
        LocalDate today = LocalDate.now();
        boolean hasVigente = false;
        boolean hasPorVencer = false;

        for (InventoryEntry entry : entries) {
            LocalDate expirationDate = entry.getExpirationDate();
            if (expirationDate.isBefore(today)) {
                return "Vencido";
            } else if (expirationDate.isBefore(today.plusDays(3))) {
                hasPorVencer = true;
            } else {
                hasVigente = true;
            }
        }

        if (hasPorVencer) return "Por vencer";
        if (hasVigente) return "Vigente";
        return "Sin inventario";
    }
}
