package com.sistema.blog.sistemablogspring.DTOS;

import lombok.Data;

@Data
public class InventoryExitDTO {
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEntryId() {
		return entryId;
	}
	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	private Long id;
    private Long entryId; 
    private int quantity;
}