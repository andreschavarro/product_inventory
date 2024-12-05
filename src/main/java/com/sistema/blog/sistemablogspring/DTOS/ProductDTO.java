package com.sistema.blog.sistemablogspring.DTOS;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ProductDTO {
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ProductDTO(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public ProductDTO() {
		//TODO Auto-generated constructor stub
	}

	private Long id;
    private String name;
    private String description;
	private String imageUrl;

}