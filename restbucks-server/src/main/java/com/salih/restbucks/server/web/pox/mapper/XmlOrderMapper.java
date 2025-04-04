package com.salih.restbucks.server.web.pox.mapper;

import java.util.List;
import java.util.UUID;

import com.salih.restbucks.server.domain.Attribute;
import com.salih.restbucks.server.domain.ConsumeLocation;
import com.salih.restbucks.server.domain.Item;
import com.salih.restbucks.server.domain.Order;
import com.salih.restbucks.server.domain.OrderStatus;
import com.salih.restbucks.server.domain.ProductType;
import com.salih.restbucks.server.domain.PropertyKey;
import com.salih.restbucks.server.web.pox.xmlmodel.Product;

public class XmlOrderMapper {

	public static Order map(com.salih.restbucks.server.web.pox.xmlmodel.Order xmlOrder) {
		List<Item> domainItems = xmlOrder.getItems().stream() //
				.map(XmlOrderMapper::mapItem) //
				.toList();

		return new Order(UUID.randomUUID().toString(), //
				domainItems, //
				ConsumeLocation.valueOf(xmlOrder.getLocation().value()), //
				OrderStatus.PENDING, //
				0.0, //
				xmlOrder.getCurrency());
	}

	private static Item mapItem(com.salih.restbucks.server.web.pox.xmlmodel.Item xmlItem) {
		Product xmlProduct = xmlItem.getProduct();

		List<Attribute> attributes = xmlProduct.getAttribute().stream() //
				.map(attribute -> new Attribute(PropertyKey.valueOf(attribute.getName().name()), attribute.getValue())) //
				.toList();

		com.salih.restbucks.server.domain.Product product = new com.salih.restbucks.server.domain.Product( //
				xmlProduct.getName(), //
				ProductType.valueOf(xmlProduct.getType().name()), //
				attributes.stream().map(Attribute::propertyKey).toList());

		return new Item(product, xmlItem.getQuantity(), attributes);
	}
}
