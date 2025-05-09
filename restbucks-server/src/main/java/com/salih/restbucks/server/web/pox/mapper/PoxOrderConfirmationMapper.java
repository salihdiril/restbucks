package com.salih.restbucks.server.web.pox.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.common.log.helpers.XmlStringSerializer;
import com.salih.restbucks.server.domain.Order;
import com.salih.restbucks.server.web.mapper.ToXmlMapper;
import com.salih.restbucks.server.web.pox.xmlmodel.OrderConfirmation;
import com.salih.restbucks.server.web.pox.xmlmodel.OrderStatus;

@Component
public class PoxOrderConfirmationMapper implements ToXmlMapper<OrderConfirmation>, Loggable {

	@Override
	public OrderConfirmation toXml(Order order) {
		return map(order);
	}

	private OrderConfirmation map(Order order) {
		logEnter();

		List<String> items = order.getItems().stream() //
				.map(item -> item.getProduct().name()) //
				.toList();

		OrderConfirmation confirmation = new OrderConfirmation();
		confirmation.setOrderId(order.getOrderId());
		confirmation.getItemNames().addAll(items);
		confirmation.setCost(order.getCost());
		confirmation.setCurrency(order.getCurrency());
		confirmation.setStatus(OrderStatus.valueOf(order.getStatus().name()));
		logger().atDebug().log("Domain Order converted to XML Order: \n {}", XmlStringSerializer.toXmlString(confirmation));

		logExit();
		return confirmation;
	}
}
