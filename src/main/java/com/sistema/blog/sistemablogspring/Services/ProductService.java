package com.sistema.blog.sistemablogspring.Services;

import java.util.List;
import java.util.Optional;

import com.sistema.blog.sistemablogspring.DTOS.ProductDTO;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    List<ProductDTO> getAllProducts(); 
    Optional<ProductDTO> getProductById(Long id); 
    void deleteProduct(Long id); 
}