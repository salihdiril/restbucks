package com.salih.restbucks.server.web.tunneling.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.salih.restbucks.server.domain.Attribute;
import com.salih.restbucks.server.domain.Item;
import com.salih.restbucks.server.domain.Order;
import com.salih.restbucks.server.domain.Product;
import com.salih.restbucks.server.domain.ProductType;
import com.salih.restbucks.server.domain.PropertyKey;
import com.salih.restbucks.server.service.ProductCatalog;
import com.salih.restbucks.server.web.tunneling.util.OrderUriParser;

@ExtendWith(MockitoExtension.class)
public class OrderUriParserTest {
	@Mock
	ProductCatalog productCatalog;

	Product dummyProduct;

	@BeforeEach
	void setUp() {
		dummyProduct = new Product("ESPRESSO", ProductType.DRINK, List.of(PropertyKey.SHOT));
	}

	@Test
	void shouldParseValidParamsToOrder() {
		when(productCatalog.findByName("ESPRESSO")).thenReturn(dummyProduct);

		Map<String, String> params = Map.of("product", "ESPRESSO", "quantity", "2", "shot", "double");
		Order order = OrderUriParser.parse(params, productCatalog);
		assertNotNull(order);
		assertEquals(1, order.getItems().size());

		Item item = order.getItems().get(0);
		assertEquals(dummyProduct, item.product());
		assertEquals(2, item.quantity());

		Attribute attr = item.attributes().get(0);
		assertEquals(PropertyKey.SHOT, attr.propertyKey());
		assertEquals("double", attr.value());
	}

	@Test
	void shouldReturnNullWhenProductIsMissing() {
		Map<String, String> params = Map.of("quantity", "1");
		Order order = OrderUriParser.parse(params, productCatalog);
		assertNull(order);
	}

	@Test
	void shouldThrowWhenInvalidQuantity() {
		Map<String, String> params = Map.of("product", "ESPRESSO", "quantity", "two");
		assertThrows(NumberFormatException.class, () -> OrderUriParser.parse(params, productCatalog));
	}

	@Test
	void shouldParseWithDefaultQuantity() {
		when(productCatalog.findByName("ESPRESSO")).thenReturn(dummyProduct);

		Map<String, String> params = Map.of("product", "ESPRESSO");
		Order order = OrderUriParser.parse(params, productCatalog);
		assertEquals(1, order.getItems().get(0).quantity());
	}

	@Test
	void shouldThrowWhenInvalidAttributeKey() {
		Map<String, String> params = Map.of("product", "ESPRESSO", "quantity", "1", "milkiness", "full");
		assertThrows(IllegalArgumentException.class, () -> OrderUriParser.parse(params, productCatalog));
	}

	@Test
	void shouldParseCaseInsensitiveProduct() {
		when(productCatalog.findByName("HOT_CHOCOLATE")).thenReturn(new Product("HOT_CHOCOLATE", ProductType.DRINK, List.of()));

		Map<String, String> params = Map.of("product", "hot_chocolate");
		Order order = OrderUriParser.parse(params, productCatalog);
		assertEquals("HOT_CHOCOLATE", order.getItems().get(0).product().name());
	}
}
