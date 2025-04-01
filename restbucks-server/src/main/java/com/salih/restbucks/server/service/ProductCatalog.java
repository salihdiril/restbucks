package com.salih.restbucks.server.service;

import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.salih.restbucks.server.domain.MenuItem;
import com.salih.restbucks.server.domain.Product;
import com.salih.restbucks.server.domain.ProductType;
import com.salih.restbucks.server.domain.PropertyKey;

@Service
public class ProductCatalog {
	private final Map<MenuItem, Product> productMap = new EnumMap<>(MenuItem.class);

	public ProductCatalog() {
		productMap.put(MenuItem.LATTE, new Product(MenuItem.LATTE.name(), ProductType.DRINK, List.of(PropertyKey.MILK)));
		productMap.put(MenuItem.CAPPUCCINO, new Product(MenuItem.CAPPUCCINO.name(), ProductType.DRINK, List.of(PropertyKey.SIZE)));
		productMap.put(MenuItem.ESPRESSO, new Product(MenuItem.ESPRESSO.name(), ProductType.DRINK, List.of(PropertyKey.SHOT)));
		productMap.put(MenuItem.TEA, new Product(MenuItem.TEA.name(), ProductType.DRINK, List.of()));
		productMap.put(MenuItem.HOT_CHOCOLATE,
				new Product(MenuItem.HOT_CHOCOLATE.name(), ProductType.DRINK, List.of(PropertyKey.MILK, PropertyKey.SIZE, PropertyKey.WHIPPED_CREAM)));
		productMap.put(MenuItem.COOKIE, new Product(MenuItem.COOKIE.name(), ProductType.FOOD, List.of(PropertyKey.KIND)));
	}

	public Product findByName(String name) {
		Product product = productMap.get(MenuItem.valueOf(name));
		if (product == null) {
			throw new IllegalArgumentException("Product not found: " + name);
		}
		return product;
	}

	public Collection<Product> allProducts() {
		return productMap.values();
	}
}
