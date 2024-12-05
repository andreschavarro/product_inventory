package com.sistema.blog.sistemablogspring.Mappers;

import com.sistema.blog.sistemablogspring.DTOS.ProductStateDTO;
import com.sistema.blog.sistemablogspring.Entities.Product;

public class ProductStateMapper {

    public static ProductStateDTO toDTO(Product product, int totalQuantity, String state) {
        if (product == null) return null;

        ProductStateDTO dto = new ProductStateDTO();
        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setTotalQuantity(totalQuantity);
        dto.setState(state);
        return dto;
    }
}