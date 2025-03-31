package com.salih.restbucks.web.util;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.salih.restbucks.domain.Attribute;
import com.salih.restbucks.domain.ConsumeLocation;
import com.salih.restbucks.domain.Item;
import com.salih.restbucks.domain.MenuItem;
import com.salih.restbucks.domain.Order;
import com.salih.restbucks.domain.OrderStatus;
import com.salih.restbucks.domain.Product;
import com.salih.restbucks.domain.PropertyKey;
import com.salih.restbucks.service.ProductCatalog;

public enum OrderUriParser {
	INSTANCE;

	public static Order parse(Map<String, String> params, ProductCatalog productCatalog) {
		String productParam = params.get("product");
		String quantityParam = params.getOrDefault("quantity", "1");

		if (productParam == null) {
			return null;
		}

		MenuItem menuItem = MenuItem.valueOf(productParam.toUpperCase());
		Product product = productCatalog.findByName(menuItem.name());

		List<Attribute> attributes = params.entrySet().stream()
				.filter(e -> !e.getKey().equals("product") && !e.getKey().equals("quantity"))
				.map(e -> new Attribute(PropertyKey.valueOf(e.getKey().toUpperCase()), e.getValue()))
				.toList();

		Item item = new Item(product, Integer.parseInt(quantityParam), attributes);
		return new Order(UUID.randomUUID().toString(), List.of(item), ConsumeLocation.TAKE_AWAY, OrderStatus.PENDING, 0.0, "USD");
	}
}
