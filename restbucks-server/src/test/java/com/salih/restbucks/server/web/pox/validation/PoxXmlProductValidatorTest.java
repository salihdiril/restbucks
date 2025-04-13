package com.salih.restbucks.server.web.pox.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.salih.restbucks.server.web.pox.xmlmodel.Attribute;
import com.salih.restbucks.server.web.pox.xmlmodel.Product;
import com.salih.restbucks.server.web.pox.xmlmodel.ProductType;
import com.salih.restbucks.server.web.pox.xmlmodel.PropertyKey;

@SpringBootTest
public class PoxXmlProductValidatorTest {

	@MockitoBean
	private PoxXmlAttributeValidator attributeValidator;
	@Autowired
	private PoxXmlProductValidator productValidator;

	@Test
	void shouldValidateValidProduct() {
		Product xmlProduct = new Product();
		xmlProduct.setName("Cappuccino");
		xmlProduct.setType(ProductType.DRINK);

		Attribute xmlAttribute1 = new Attribute();
		xmlAttribute1.setName(PropertyKey.SIZE);
		xmlAttribute1.setValue("large");

		Attribute xmlAttribute2 = new Attribute();
		xmlAttribute1.setName(PropertyKey.WHIPPED_CREAM);
		xmlAttribute1.setValue("no");

		List<Attribute> xmlAttributes = List.of(xmlAttribute1, xmlAttribute2);

		xmlProduct.getAttribute().addAll(xmlAttributes);

		assertDoesNotThrow(() -> productValidator.validate(xmlProduct));

		verify(attributeValidator, times(2)).validate(any(Attribute.class));
	}

	@Test
	void shouldThrowWhenProductTypeIsNull() {
		Product xmlProduct = new Product();
		xmlProduct.setName("Cappuccino");

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> productValidator.validate(xmlProduct));
		assertEquals("Missing or invalid enum value for field: product.type", ex.getMessage());
	}
}
