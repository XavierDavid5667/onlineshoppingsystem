package com.orderservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.orderservice.dto.InventoryResponse;


@FeignClient(name = "INVENTORY-SERVICE",url = "http://localhost:8082")
public interface InventoryClient {
	
	@GetMapping("/api/inventory")
	public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode);

}
