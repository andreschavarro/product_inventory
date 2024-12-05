package com.sistema.blog.sistemablogspring.Repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.blog.sistemablogspring.Entities.InventoryEntry;

@Repository
public interface InventoryEntryRepository extends JpaRepository<InventoryEntry, Long> {

    List<InventoryEntry> findByProductId(Long productId);
}