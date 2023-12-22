package com.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orderservice.dto.OrderRequest;
import com.orderservice.model.Order;
import com.orderservice.model.OrderLineItems;
import com.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	public void placeOrder(OrderRequest orderRequest) {
		Order order=new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> list = orderRequest.getOrderLineItemsDtoList().stream().map(e->{
			OrderLineItems orderLineItems=new OrderLineItems();
			orderLineItems.setPrice(e.getPrice());
			orderLineItems.setQuantity(e.getQuantity());
			orderLineItems.setSkuCode(e.getSkuCode());
			return orderLineItems;
		}).toList();
		order.setOrderLineItemsList(list);
		orderRepository.save(order);
		
	}

}
