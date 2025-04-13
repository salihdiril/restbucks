package com.salih.restbucks.server.web.pox.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.salih.restbucks.server.web.pox.xmlmodel.Item;
import com.salih.restbucks.server.web.pox.xmlmodel.Product;
import com.salih.restbucks.server.web.pox.xmlmodel.ProductType;

@SpringBootTest
public class PoxXmlItemValidatorTest {

	@MockitoBean
	private PoxXmlProductValidator productValidator;
	@Autowired
	private PoxXmlItemValidator itemValidator;

	@Test
	void shouldValidateValidItem() {
		Product xmlProduct = new Product();
		xmlProduct.setName("Cappuccino");
		xmlProduct.setType(ProductType.DRINK);

		Item xmlItem = new Item();
		xmlItem.setProduct(xmlProduct);
		xmlItem.setQuantity(2);

		assertDoesNotThrow(() -> itemValidator.validate(xmlItem));

		verify(productValidator).validate(xmlProduct);
	}

	@Test
	void shouldThrowWhenQuantityIsZero() {
		Product xmlProduct = new Product();
		xmlProduct.setName("Cappuccino");
		xmlProduct.setType(ProductType.DRINK);

		Item xmlItem = new Item();
		xmlItem.setProduct(xmlProduct);
		xmlItem.setQuantity(0);

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> itemValidator.validate(xmlItem));
		assertEquals("item.quantity must be a positive number.", ex.getMessage());

		verify(productValidator, never()).validate(any());
	}

	@Test
	void shouldThrowWhenQuantityIsNegative() {
		Product xmlProduct = new Product();
		xmlProduct.setName("Cappuccino");
		xmlProduct.setType(ProductType.DRINK);

		Item xmlItem = new Item();
		xmlItem.setProduct(xmlProduct);
		xmlItem.setQuantity(-5);

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> itemValidator.validate(xmlItem));
		assertEquals("item.quantity must be a positive number.", ex.getMessage());

		verify(productValidator, never()).validate(any());
	}
}
