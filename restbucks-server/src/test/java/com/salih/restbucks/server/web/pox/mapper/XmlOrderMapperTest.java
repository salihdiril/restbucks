package com.salih.restbucks.server.web.pox.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.salih.restbucks.server.domain.OrderStatus;
import com.salih.restbucks.server.web.pox.xmlmodel.Attribute;
import com.salih.restbucks.server.web.pox.xmlmodel.ConsumeLocation;
import com.salih.restbucks.server.web.pox.xmlmodel.Item;
import com.salih.restbucks.server.web.pox.xmlmodel.Items;
import com.salih.restbucks.server.web.pox.xmlmodel.Order;
import com.salih.restbucks.server.web.pox.xmlmodel.Product;
import com.salih.restbucks.server.web.pox.xmlmodel.ProductType;
import com.salih.restbucks.server.web.pox.xmlmodel.PropertyKey;

public class XmlOrderMapperTest {
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

		com.salih.restbucks.server.domain.Order domainOrder = XmlOrderMapper.map(xmlOrder);

		assertNotNull(domainOrder);
		assertEquals(1, domainOrder.getItems().size());

		com.salih.restbucks.server.domain.Item domainItem = domainOrder.getItems().get(0);
		assertEquals("Latte", domainItem.product().name());
		assertEquals(com.salih.restbucks.server.domain.ProductType.DRINK, domainItem.product().type());
		assertEquals(2, domainItem.quantity());
		assertEquals(1, domainItem.attributes().size());
		assertEquals(com.salih.restbucks.server.domain.PropertyKey.SIZE, domainItem.attributes().get(0).propertyKey());
		assertEquals("Large", domainItem.attributes().get(0).value());

		assertEquals("USD", domainOrder.getCurrency());
		assertEquals(OrderStatus.PENDING, domainOrder.getStatus());
		assertEquals(com.salih.restbucks.server.domain.ConsumeLocation.IN_SHOP, domainOrder.getLocation());
	}
}
