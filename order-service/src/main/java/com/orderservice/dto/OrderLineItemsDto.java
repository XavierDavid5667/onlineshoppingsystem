package com.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderLineItemsDto {
	
	private Long id;
	private String skuCode;
	private Long price;
	private Integer quantity;

}
