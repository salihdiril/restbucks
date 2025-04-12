package com.salih.restbucks.server.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.salih.restbucks.common.exception.domain.ProductNotFoundException;
import com.salih.restbucks.server.domain.Product;

public class ProductCatalogTest {
	private ProductCatalog productCatalog;

	@BeforeEach
	void setUp() {
		productCatalog = new ProductCatalog();
	}

	@Test
	void shouldContainAllPredefinedMenuItems() {
		assertEquals(6, productCatalog.allProducts().size());
		assertNotNull(productCatalog.findByName("ESPRESSO"));
		assertNotNull(productCatalog.findByName("HOT_CHOCOLATE"));
	}

	@Test
	void shouldReturnCorrectProductForMenuItem() {
		Product espresso = productCatalog.findByName("ESPRESSO");
		assertEquals("ESPRESSO", espresso.name());
	}

	@Test
	void shouldThrowExceptionWhenProductNotFound() {
		assertThrows(ProductNotFoundException.class, () -> productCatalog.findByName("UNKNOWN"));
	}
}
