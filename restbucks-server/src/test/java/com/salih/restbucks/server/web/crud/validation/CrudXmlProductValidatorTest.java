package com.salih.restbucks.server.web.crud.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.salih.restbucks.server.web.crud.xmlmodel.Product;
import com.salih.restbucks.server.web.crud.xmlmodel.ProductType;

@SpringBootTest
public class CrudXmlProductValidatorTest {

	@Autowired
	private CrudXmlProductValidator productValidator;

	@Test
	void shouldValidateValidProduct() {
		Product product = new Product();
		product.setName("Espresso");
		product.setType(ProductType.DRINK);

		assertDoesNotThrow(() -> productValidator.validate(product));
	}

	@Test
	void shouldThrowWhenProductTypeIsNull() {
		Product product = new Product();
		product.setName("Espresso");

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> productValidator.validate(product));
		assertEquals("Missing or invalid enum value for field: product.type", ex.getMessage());
	}
}
