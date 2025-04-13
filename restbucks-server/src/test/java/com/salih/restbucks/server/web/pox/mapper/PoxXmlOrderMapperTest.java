package com.salih.restbucks.server.web.pox.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.salih.restbucks.server.domain.OrderStatus;
import com.salih.restbucks.server.web.pox.xmlmodel.Attribute;
import com.salih.restbucks.server.web.pox.xmlmodel.ConsumeLocation;
import com.salih.restbucks.server.web.pox.xmlmodel.Item;
import com.salih.restbucks.server.web.pox.xmlmodel.Items;
import com.salih.restbucks.server.web.pox.xmlmodel.Order;
import com.salih.restbucks.server.web.pox.xmlmodel.Product;
import com.salih.restbucks.server.web.pox.xmlmodel.ProductType;
import com.salih.restbucks.server.web.pox.xmlmodel.PropertyKey;
@SpringBootTest
public class PoxXmlOrderMapperTest {

	@Autowired
	private PoxXmlOrderMapper orderMapper;

	@Test
	void shouldMapXmlOrderToDomainOrderCorrectly() {
		Attribute xmlAttribute = new Attribute();
		xmlAttribute.setName(PropertyKey.SIZE);
		xmlAttribute.setValue("Large");

		Product xmlProduct = new Product();
		xmlProduct.setName("Latte");
		xmlProduct.setType(ProductType.DRINK);
		xmlProduct.getAttribute().add(xmlAttribute);

		Item xmlItem = new Item();
		xmlItem.setProduct(xmlProduct);
		xmlItem.setQuantity(2);

		Order xmlOrder = new Order();
		Items xmlItems = new Items();
		xmlItems.getItem().add(xmlItem);
		xmlOrder.setItems(xmlItems);
		xmlOrder.setCurrency("USD");
		xmlOrder.setLocation(ConsumeLocation.IN_SHOP);

		com.salih.restbucks.server.domain.Order domainOrder = orderMapper.toDomain(xmlOrder);

		assertNotNull(domainOrder);
		assertEquals(1, domainOrder.getItems().size());

		com.salih.restbucks.server.domain.Item domainItem = domainOrder.getItems().get(0);
		assertEquals("Latte", domainItem.getProduct().name());
		assertEquals(com.salih.restbucks.server.domain.ProductType.DRINK, domainItem.getProduct().type());
		assertEquals(2, domainItem.getQuantity());
		assertEquals(1, domainItem.getAttributes().size());
		assertEquals(com.salih.restbucks.server.domain.PropertyKey.SIZE, domainItem.getAttributes().get(0).propertyKey());
		assertEquals("Large", domainItem.getAttributes().get(0).value());

		assertEquals("USD", domainOrder.getCurrency());
		assertEquals(OrderStatus.PENDING, domainOrder.getStatus());
		assertEquals(com.salih.restbucks.server.domain.ConsumeLocation.IN_SHOP, domainOrder.getLocation());
	}

	@Test
	void shouldMapItemWithNoAttributes() {
		var xmlProduct = new Product();
		xmlProduct.setName("Latte");
		xmlProduct.setType(ProductType.DRINK);
		xmlProduct.getAttribute();

		var xmlItem = new Item();
		xmlItem.setQuantity(1);
		xmlItem.setProduct(xmlProduct);

		var xmlOrder = new Order();
		var xmlItems = new Items();
		xmlItems.getItem().add(xmlItem);
		xmlOrder.setItems(xmlItems);
		xmlOrder.setLocation(ConsumeLocation.IN_SHOP);
		xmlOrder.setCurrency("USD");

		var domainOrder = orderMapper.toDomain(xmlOrder);
		assertNotNull(domainOrder);
		assertEquals(1, domainOrder.getItems().size());
		assertEquals(0, domainOrder.getItems().get(0).getAttributes().size());
	}
}
