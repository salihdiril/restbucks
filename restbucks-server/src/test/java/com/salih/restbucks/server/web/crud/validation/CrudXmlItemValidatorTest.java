package com.salih.restbucks.server.web.crud.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.salih.restbucks.server.web.crud.xmlmodel.Attribute;
import com.salih.restbucks.server.web.crud.xmlmodel.Attributes;
import com.salih.restbucks.server.web.crud.xmlmodel.Item;
import com.salih.restbucks.server.web.crud.xmlmodel.Product;
import com.salih.restbucks.server.web.crud.xmlmodel.ProductType;
import com.salih.restbucks.server.web.crud.xmlmodel.PropertyKey;

@SpringBootTest
public class CrudXmlItemValidatorTest {

	@MockitoBean
	private CrudXmlAttributeValidator attributeValidator;
	@MockitoBean
	private CrudXmlProductValidator productValidator;
	@Autowired
	private CrudXmlItemValidator itemValidator;

	@Test
	void shouldValidateValidItem() {
		Item item = new Item();
		Attributes attributes = new Attributes();

		Product product = new Product();
		product.setName("Espresso");
		product.setType(ProductType.DRINK);

		Attribute attribute1 = new Attribute();
		attribute1.setName(PropertyKey.SIZE);
		attribute1.setValue("large");

		Attribute attribute2 = new Attribute();
		attribute2.setName(PropertyKey.SHOT);
		attribute2.setValue("double");

		attributes.getAttribute().addAll(List.of(attribute1, attribute2));
		item.setAttributes(attributes);
		item.setProduct(product);
		item.setQuantity(1);

		assertDoesNotThrow(() -> itemValidator.validate(item));
		verify(productValidator).validate(product);
		verify(attributeValidator, times(2)).validate(any(Attribute.class));
	}

	@Test
	void shouldThrowWhenQuantityIsZero() {
		Item item = new Item();

		Product product = new Product();
		product.setName("Espresso");
		product.setType(ProductType.DRINK);

		item.setProduct(product);
		item.setQuantity(0);

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> itemValidator.validate(item));
		assertEquals("item.quantity must be a positive number.", ex.getMessage());

		verify(productValidator, never()).validate(any());
		verify(attributeValidator, never()).validate(any());
	}

	@Test
	void shouldThrowWhenQuantityIsNegative() {
		Item item = new Item();

		Product product = new Product();
		product.setName("Espresso");
		product.setType(ProductType.DRINK);

		item.setProduct(product);
		item.setQuantity(-1);

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> itemValidator.validate(item));
		assertEquals("item.quantity must be a positive number.", ex.getMessage());

		verify(productValidator, never()).validate(any());
		verify(attributeValidator, never()).validate(any());
	}
}
