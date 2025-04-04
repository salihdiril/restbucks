package com.salih.restbucks.server.web.pox.mapper;

import java.util.List;

import com.salih.restbucks.server.domain.Order;
import com.salih.restbucks.server.web.pox.xmlmodel.OrderConfirmation;
import com.salih.restbucks.server.web.pox.xmlmodel.OrderStatus;

public class OrderConfirmationMapper {
	public static OrderConfirmation map(Order order) {
		List<String> items = order.getItems().stream() //
				.map(item -> item.product().name()) //
				.toList();

		OrderConfirmation confirmation = new OrderConfirmation();
		confirmation.setOrderId(order.getOrderId());
		confirmation.getItemNames().addAll(items);
		confirmation.setCost(order.getCost());
		confirmation.setCurrency(order.getCurrency());
		confirmation.setStatus(OrderStatus.valueOf(order.getStatus().name()));

		return confirmation;
	}
}
