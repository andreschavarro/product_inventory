package com.sistema.blog.sistemablogspring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.blog.sistemablogspring.Entities.InventoryExit;

@Repository
public interface InventoryExitRepository extends JpaRepository<InventoryExit, Long> {

}