package com.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderservice.client.InventoryClient;
import com.orderservice.dto.InventoryResponse;
import com.orderservice.dto.OrderRequest;
import com.orderservice.exceptions.OrderNotValidException;
import com.orderservice.model.Order;
import com.orderservice.model.OrderLineItems;
import com.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private InventoryClient inventoryClient;

	public void placeOrder(OrderRequest orderRequest) throws OrderNotValidException {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> list = orderRequest.getOrderLineItemsDtoList().stream().map(e -> {
			OrderLineItems orderLineItems = new OrderLineItems();
			orderLineItems.setPrice(e.getPrice());
			orderLineItems.setQuantity(e.getQuantity());
			orderLineItems.setSkuCode(e.getSkuCode());
			System.out.println(e.getSkuCode());
			return orderLineItems;
		}).toList();
		order.setOrderLineItemsList(list);

		List<String> list2 = order.getOrderLineItemsList().stream().map(item -> item.getSkuCode()).toList();
		System.out.println(list2);

		// call inventory service and place order if product is in stock
		List<InventoryResponse> inStock = inventoryClient.isInStock(list2);
		System.out.println(inStock);

		boolean allproductsInStock = inStock.stream().allMatch(inventory -> inventory.isInStock());
	
		if (allproductsInStock) {
			orderRepository.save(order);
		} else {
			throw new OrderNotValidException("Product is not in stock,Please try again later");
		}
	}
}
