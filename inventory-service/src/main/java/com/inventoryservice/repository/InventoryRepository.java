package com.inventoryservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventoryservice.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long>{

	List<Inventory> findBySkuCodeIn(List<String> skuCode);

}
