package com.salih.restbucks.server.service;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.salih.restbucks.common.exception.domain.ProductNotFoundException;
import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.server.domain.MenuItem;
import com.salih.restbucks.server.domain.Product;
import com.salih.restbucks.server.domain.ProductType;

@Service
public class ProductCatalog implements Loggable {
	private final Map<MenuItem, Product> productMap = new EnumMap<>(MenuItem.class);

	public ProductCatalog() {
		productMap.put(MenuItem.LATTE, new Product(MenuItem.LATTE.name(), ProductType.DRINK));
		productMap.put(MenuItem.CAPPUCCINO, new Product(MenuItem.CAPPUCCINO.name(), ProductType.DRINK));
		productMap.put(MenuItem.ESPRESSO, new Product(MenuItem.ESPRESSO.name(), ProductType.DRINK));
		productMap.put(MenuItem.TEA, new Product(MenuItem.TEA.name(), ProductType.DRINK));
		productMap.put(MenuItem.HOT_CHOCOLATE, new Product(MenuItem.HOT_CHOCOLATE.name(), ProductType.DRINK));
		productMap.put(MenuItem.COOKIE, new Product(MenuItem.COOKIE.name(), ProductType.FOOD));
	}

	public Product findByName(String name) {
		logEnter();

		try {
			Product product = productMap.get(MenuItem.valueOf(name));

			logExit();
			return product;

		} catch (IllegalArgumentException ex) {
			logger().atError().log("Product '{}' not found in product map", name);
			throw new ProductNotFoundException(name);
		}
	}

	public Collection<Product> allProducts() {
		return productMap.values();
	}
}
