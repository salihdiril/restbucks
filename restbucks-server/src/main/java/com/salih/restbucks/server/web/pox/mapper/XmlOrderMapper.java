package com.salih.restbucks.server.web.pox.mapper;

import java.util.List;
import java.util.UUID;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.common.log.StaticLogger;
import com.salih.restbucks.server.domain.Attribute;
import com.salih.restbucks.server.domain.ConsumeLocation;
import com.salih.restbucks.server.domain.Item;
import com.salih.restbucks.server.domain.Order;
import com.salih.restbucks.server.domain.OrderStatus;
import com.salih.restbucks.server.domain.ProductType;
import com.salih.restbucks.server.domain.PropertyKey;
import com.salih.restbucks.server.web.pox.xmlmodel.Product;

public class XmlOrderMapper implements Loggable {

	public static Order map(com.salih.restbucks.server.web.pox.xmlmodel.Order xmlOrder) {
		StaticLogger.logEnter(XmlOrderMapper.class);

		List<Item> domainItems = xmlOrder.getItems().getItem().stream() //
				.map(XmlOrderMapper::mapItem) //
				.toList();
		StaticLogger.logger(XmlOrderMapper.class).atTrace().log("Extracted item names: {}", domainItems);

		StaticLogger.logExit(XmlOrderMapper.class);
		return new Order(UUID.randomUUID().toString(), //
				domainItems, //
				ConsumeLocation.valueOf(xmlOrder.getLocation().value()), //
				OrderStatus.PENDING, //
				0.0, //
				xmlOrder.getCurrency());
	}

	private static Item mapItem(com.salih.restbucks.server.web.pox.xmlmodel.Item xmlItem) {
		StaticLogger.logEnter(XmlOrderMapper.class);
		Product xmlProduct = xmlItem.getProduct();
		StaticLogger.logger(XmlOrderMapper.class).atDebug().log("Mapping xmlItem for product: {}, quantity: {}", xmlProduct.getName(), xmlItem.getQuantity());

		List<Attribute> attributes = xmlProduct.getAttribute().stream() //
				.map(attribute -> new Attribute(PropertyKey.valueOf(attribute.getName().name()), attribute.getValue())) //
				.toList();
		StaticLogger.logger(XmlOrderMapper.class).atTrace().log("Mapped attributes: {}", attributes);

		com.salih.restbucks.server.domain.Product product = new com.salih.restbucks.server.domain.Product( //
				xmlProduct.getName(), //
				ProductType.valueOf(xmlProduct.getType().name()), //
				attributes.stream().map(Attribute::propertyKey).toList());
		StaticLogger.logger(XmlOrderMapper.class).atTrace().log("Created domain product: {}", product);

		StaticLogger.logExit(XmlOrderMapper.class);
		return new Item(product, xmlItem.getQuantity(), attributes);
	}
}
