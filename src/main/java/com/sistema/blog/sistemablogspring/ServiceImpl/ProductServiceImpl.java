package com.sistema.blog.sistemablogspring.ServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sistema.blog.sistemablogspring.DTOS.ProductDTO;
import com.sistema.blog.sistemablogspring.Entities.Product;
import com.sistema.blog.sistemablogspring.Mappers.ProductMapper;
import com.sistema.blog.sistemablogspring.Repositories.ProductRepository;
import com.sistema.blog.sistemablogspring.Services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
}