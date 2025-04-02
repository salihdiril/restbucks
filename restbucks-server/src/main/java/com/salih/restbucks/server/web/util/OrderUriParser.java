package com.salih.restbucks.server.web.util;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.common.log.StaticLogger;
import com.salih.restbucks.server.domain.Attribute;
import com.salih.restbucks.server.domain.ConsumeLocation;
import com.salih.restbucks.server.domain.Item;
import com.salih.restbucks.server.domain.MenuItem;
import com.salih.restbucks.server.domain.Order;
import com.salih.restbucks.server.domain.OrderStatus;
import com.salih.restbucks.server.domain.Product;
import com.salih.restbucks.server.domain.PropertyKey;
import com.salih.restbucks.server.service.ProductCatalog;

public enum OrderUriParser implements Loggable {
	;

	public static Order parse(Map<String, String> params, ProductCatalog productCatalog) {
		StaticLogger.logEnter(OrderUriParser.class);
		String productParam = params.get("product");
		String quantityParam = params.getOrDefault("quantity", "1");

		if (productParam == null) {
			return null;
		}

		MenuItem menuItem = MenuItem.valueOf(productParam.toUpperCase());
		Product product = productCatalog.findByName(menuItem.name());

		List<Attribute> attributes = params.entrySet().stream() //
				.filter(e -> !e.getKey().equals("product") && !e.getKey().equals("quantity")) //
				.map(e -> new Attribute(PropertyKey.valueOf(e.getKey().toUpperCase()), e.getValue())) //
				.toList();

		Item item = new Item(product, Integer.parseInt(quantityParam), attributes);
		StaticLogger.logExit(OrderUriParser.class);
		return new Order(UUID.randomUUID().toString(), List.of(item), ConsumeLocation.TAKE_AWAY, OrderStatus.PENDING, 0.0, "USD");
	}
}
