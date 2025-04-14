package com.salih.restbucks.server.web.testutil;

import com.salih.restbucks.server.web.crud.xmlmodel.Attribute;
import com.salih.restbucks.server.web.crud.xmlmodel.Attributes;
import com.salih.restbucks.server.web.crud.xmlmodel.ConsumeLocation;
import com.salih.restbucks.server.web.crud.xmlmodel.Item;
import com.salih.restbucks.server.web.crud.xmlmodel.Items;
import com.salih.restbucks.server.web.crud.xmlmodel.Order;
import com.salih.restbucks.server.web.crud.xmlmodel.Product;
import com.salih.restbucks.server.web.crud.xmlmodel.ProductType;
import com.salih.restbucks.server.web.crud.xmlmodel.PropertyKey;

public class TestXmlOrderFactory {

	private TestXmlOrderFactory() {
	}

	public static Order createValidCrudXmlOrder() {
		Order xmlOrder = new Order();
		Items xmlItems = new Items();
		Attributes xmlAttributes = new Attributes();
		Attribute xmlAttribute = new Attribute();
		Item xmlItem = new Item();
		Product xmlProduct = new Product();

		xmlProduct.setType(ProductType.DRINK);
		xmlProduct.setName("ESPRESSO");

		xmlAttribute.setName(PropertyKey.SHOT);
		xmlAttribute.setValue("double");
		xmlAttributes.getAttribute().add(xmlAttribute);

		xmlItem.setProduct(xmlProduct);
		xmlItem.setQuantity(1);
		xmlItem.setAttributes(xmlAttributes);
		xmlItems.getItem().add(xmlItem);

		xmlOrder.setLocation(ConsumeLocation.TAKE_AWAY);
		xmlOrder.setCurrency("usd");
		xmlOrder.setItems(xmlItems);

		return xmlOrder;
	}

	public static com.salih.restbucks.server.web.pox.xmlmodel.Order createValidPoxXmlOrder() {
		com.salih.restbucks.server.web.pox.xmlmodel.Attribute xmlAttribute = new com.salih.restbucks.server.web.pox.xmlmodel.Attribute();
		xmlAttribute.setName(com.salih.restbucks.server.web.pox.xmlmodel.PropertyKey.SIZE);
		xmlAttribute.setValue("Large");

		com.salih.restbucks.server.web.pox.xmlmodel.Product xmlProduct = new com.salih.restbucks.server.web.pox.xmlmodel.Product();
		xmlProduct.setName("Latte");
		xmlProduct.setType(com.salih.restbucks.server.web.pox.xmlmodel.ProductType.DRINK);
		xmlProduct.getAttribute().add(xmlAttribute);

		com.salih.restbucks.server.web.pox.xmlmodel.Item xmlItem = new com.salih.restbucks.server.web.pox.xmlmodel.Item();
		xmlItem.setProduct(xmlProduct);
		xmlItem.setQuantity(2);

		com.salih.restbucks.server.web.pox.xmlmodel.Order xmlOrder = new com.salih.restbucks.server.web.pox.xmlmodel.Order();
		com.salih.restbucks.server.web.pox.xmlmodel.Items xmlItems = new com.salih.restbucks.server.web.pox.xmlmodel.Items();
		xmlItems.getItem().add(xmlItem);
		xmlOrder.setItems(xmlItems);
		xmlOrder.setCurrency("USD");
		xmlOrder.setLocation(com.salih.restbucks.server.web.pox.xmlmodel.ConsumeLocation.IN_SHOP);

		return xmlOrder;
	}

	public static Order createCrudXmlOrderWithMissingAttribute() {
		Order xmlOrder = new Order();
		Items xmlItems = new Items();
		Item xmlItem = new Item();
		Product xmlProduct = new Product();

		xmlProduct.setType(ProductType.DRINK);
		xmlProduct.setName("ESPRESSO");

		xmlItem.setProduct(xmlProduct);
		xmlItem.setQuantity(1);
		xmlItems.getItem().add(xmlItem);

		xmlOrder.setLocation(ConsumeLocation.TAKE_AWAY);
		xmlOrder.setCurrency("usd");
		xmlOrder.setItems(xmlItems);

		return xmlOrder;
	}

	public static com.salih.restbucks.server.web.pox.xmlmodel.Order createPoxXmlOrderWithMissingAttribute() {
		var xmlProduct = new com.salih.restbucks.server.web.pox.xmlmodel.Product();
		xmlProduct.setName("Latte");
		xmlProduct.setType(com.salih.restbucks.server.web.pox.xmlmodel.ProductType.DRINK);
		xmlProduct.getAttribute();

		var xmlItem = new com.salih.restbucks.server.web.pox.xmlmodel.Item();
		xmlItem.setQuantity(1);
		xmlItem.setProduct(xmlProduct);

		var xmlOrder = new com.salih.restbucks.server.web.pox.xmlmodel.Order();
		var xmlItems = new com.salih.restbucks.server.web.pox.xmlmodel.Items();
		xmlItems.getItem().add(xmlItem);
		xmlOrder.setItems(xmlItems);
		xmlOrder.setLocation(com.salih.restbucks.server.web.pox.xmlmodel.ConsumeLocation.IN_SHOP);
		xmlOrder.setCurrency("USD");

		return xmlOrder;
	}
}
