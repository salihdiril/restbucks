package com.salih.restbucks.server.web.pox.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.salih.restbucks.server.domain.ConsumeLocation;
import com.salih.restbucks.server.domain.Item;
import com.salih.restbucks.server.domain.Order;
import com.salih.restbucks.server.domain.OrderStatus;
import com.salih.restbucks.server.domain.Product;
import com.salih.restbucks.server.domain.ProductType;
import com.salih.restbucks.server.web.pox.xmlmodel.OrderConfirmation;

public class OrderConfirmationMapperTest {
	@Test
	void shouldMapOrderToOrderConfirmationCorrectly() {
		String orderId = UUID.randomUUID().toString();
		Product product = new Product("LATTE", ProductType.DRINK, List.of());
		Item item = new Item(product, 2, List.of());
		Order order = new Order(orderId, List.of(item), ConsumeLocation.IN_SHOP, OrderStatus.PAID, 5.75, "USD");

		OrderConfirmation orderConfirmation = OrderConfirmationMapper.map(order);

		assertNotNull(orderConfirmation);
		assertEquals(orderId, orderConfirmation.getOrderId());
		assertEquals(1, orderConfirmation.getItemNames().size());
		assertEquals("LATTE", orderConfirmation.getItemNames().get(0));
		assertEquals(5.75, orderConfirmation.getCost());
		assertEquals("USD", orderConfirmation.getCurrency());
		assertEquals(com.salih.restbucks.server.web.pox.xmlmodel.OrderStatus.PAID, orderConfirmation.getStatus());
	}
}
