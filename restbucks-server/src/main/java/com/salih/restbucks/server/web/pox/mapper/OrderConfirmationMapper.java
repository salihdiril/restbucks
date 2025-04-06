package com.salih.restbucks.server.web.pox.mapper;

import java.util.List;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.common.log.StaticLogger;
import com.salih.restbucks.server.domain.Order;
import com.salih.restbucks.server.web.pox.xmlmodel.OrderConfirmation;
import com.salih.restbucks.server.web.pox.xmlmodel.OrderStatus;

public class OrderConfirmationMapper implements Loggable {
	public static OrderConfirmation map(Order order) {
		StaticLogger.logEnter(OrderConfirmationMapper.class);
		StaticLogger.logger(OrderConfirmationMapper.class).atDebug().log("Mapping orderId={}, itemCount={}", order.getOrderId(), order.getItems().size());

		List<String> items = order.getItems().stream() //
				.map(item -> item.product().name()) //
				.toList();
		StaticLogger.logger(OrderConfirmationMapper.class).atTrace().log("Extracted item names: {}", items);

		OrderConfirmation confirmation = new OrderConfirmation();
		confirmation.setOrderId(order.getOrderId());
		confirmation.getItemNames().addAll(items);
		confirmation.setCost(order.getCost());
		confirmation.setCurrency(order.getCurrency());
		confirmation.setStatus(OrderStatus.valueOf(order.getStatus().name()));
		StaticLogger.logger(OrderConfirmationMapper.class).atDebug().log("Mapped OrderConfirmation: {}", confirmation);


		StaticLogger.logExit(OrderConfirmationMapper.class);
		return confirmation;
	}
}
