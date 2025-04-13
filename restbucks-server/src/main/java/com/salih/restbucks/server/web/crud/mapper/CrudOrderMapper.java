package com.salih.restbucks.server.web.crud.mapper;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.common.log.helpers.JsonStringSerializer;
import com.salih.restbucks.common.log.helpers.XmlStringSerializer;
import com.salih.restbucks.server.domain.Attribute;
import com.salih.restbucks.server.domain.ConsumeLocation;
import com.salih.restbucks.server.domain.Item;
import com.salih.restbucks.server.domain.OrderStatus;
import com.salih.restbucks.server.domain.ProductType;
import com.salih.restbucks.server.domain.PropertyKey;
import com.salih.restbucks.server.web.crud.xmlmodel.Attributes;
import com.salih.restbucks.server.web.crud.xmlmodel.Items;
import com.salih.restbucks.server.web.crud.xmlmodel.Order;
import com.salih.restbucks.server.web.crud.xmlmodel.Product;
import com.salih.restbucks.server.web.mapper.ToDomainMapper;
import com.salih.restbucks.server.web.mapper.ToXmlMapper;

@Component
public class CrudOrderMapper implements ToDomainMapper<Order>, ToXmlMapper<Order>, Loggable {
	@Override
	public com.salih.restbucks.server.domain.Order toDomain(Order xml) {
		logEnter();

		List<Item> items = xml.getItems().getItem().stream() //
				.map(this::toDomainItem) //
				.toList();

		com.salih.restbucks.server.domain.Order order = new com.salih.restbucks.server.domain.Order( //
				UUID.randomUUID().toString(), //
				items, //
				ConsumeLocation.valueOf(xml.getLocation().name()), //
				xml.getCurrency());

		order.setStatus(OrderStatus.PENDING);
		order.setCost(0.0);
		logger().atDebug().log("JAXB generated Order converted to Domain Order: \n {}", JsonStringSerializer.toJsonString(order));

		logExit();
		return order;
	}

	@Override
	public Order toXml(com.salih.restbucks.server.domain.Order order) {
		logEnter();

		Order xmlOrder = new Order();
		xmlOrder.setId(order.getOrderId());
		xmlOrder.setStatus(com.salih.restbucks.server.web.crud.xmlmodel.OrderStatus.valueOf(order.getStatus().name()));
		xmlOrder.setCost(order.getCost());
		xmlOrder.setLocation(com.salih.restbucks.server.web.crud.xmlmodel.ConsumeLocation.valueOf(order.getLocation().name()));
		xmlOrder.setCurrency(order.getCurrency());

		Items xmlItems = new Items();
		xmlItems.getItem().addAll(order.getItems().stream().map(this::toXmlItem).toList());
		xmlOrder.setItems(xmlItems);
		logger().atDebug().log("Domain Order converted to XML Order: \n {}", XmlStringSerializer.toXmlString(xmlOrder));

		logExit();
		return xmlOrder;
	}

	private Item toDomainItem(com.salih.restbucks.server.web.crud.xmlmodel.Item xmlItem) {
		logEnter();
		Product xmlProduct = xmlItem.getProduct();

		List<Attribute> attributes = xmlItem.getAttributes().getAttribute().stream() //
				.map(attribute -> new Attribute(PropertyKey.valueOf(attribute.getName().name()), attribute.getValue())) //
				.toList();

		com.salih.restbucks.server.domain.Product product = new com.salih.restbucks.server.domain.Product(xmlProduct.getName(),
				ProductType.valueOf(xmlProduct.getType().name()));

		logExit();
		return new Item(product, xmlItem.getQuantity()).setAttributes(attributes);
	}

	private com.salih.restbucks.server.web.crud.xmlmodel.Item toXmlItem(Item item) {
		logEnter();

		Product xmlProduct = new Product();
		xmlProduct.setName(item.getProduct().name());
		xmlProduct.setType(com.salih.restbucks.server.web.crud.xmlmodel.ProductType.valueOf(item.getProduct().type().name()));

		Attributes xmlAttributes = new Attributes();
		xmlAttributes.getAttribute().addAll( //
				item.getAttributes().stream() //
						.map(attribute -> { //
							com.salih.restbucks.server.web.crud.xmlmodel.Attribute xmlAttr = new com.salih.restbucks.server.web.crud.xmlmodel.Attribute(); //
							xmlAttr.setName(com.salih.restbucks.server.web.crud.xmlmodel.PropertyKey.valueOf(attribute.propertyKey().name())); //
							xmlAttr.setValue(attribute.value()); //
							return xmlAttr; //
						}) //
						.toList() //
		);

		com.salih.restbucks.server.web.crud.xmlmodel.Item xmlItem = new com.salih.restbucks.server.web.crud.xmlmodel.Item();
		xmlItem.setProduct(xmlProduct);
		xmlItem.setQuantity(item.getQuantity());
		xmlItem.setAttributes(xmlAttributes);

		logExit();
		return xmlItem;
	}
}
