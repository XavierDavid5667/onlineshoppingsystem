package com.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventoryservice.dto.InventoryResponse;
import com.inventoryservice.model.Inventory;
import com.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
	
 private final InventoryRepository inventoryRepository;
 
 @Transactional
 public List<InventoryResponse> isInStock(List<String> skuCode){
	 
	  List<Inventory> findBySkuCodeIn = inventoryRepository.findBySkuCodeIn(skuCode);
	 List<InventoryResponse> list = findBySkuCodeIn.stream().map(inventory->
			                       InventoryResponse.builder().skuCode(inventory.getSkuCode()).isInStock(inventory.getQuantity()>0).build()
	     ).toList();
//	 System.out.println(findBySkuCodeIn);
//	 System.out.println(list);
	 return list;
			                                         
 }
 
}
