package com.salih.restbucks.server.web.crud.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.salih.restbucks.server.web.crud.xmlmodel.Attribute;
import com.salih.restbucks.server.web.crud.xmlmodel.PropertyKey;

@SpringBootTest
public class CrudXmlAttributeValidatorTest {

	@Autowired
	private CrudXmlAttributeValidator attributeValidator;

	@Test
	void shouldNotThrowWhenAttributeIsValid() {
		Attribute attribute = new Attribute();
		attribute.setName(PropertyKey.SIZE);
		attribute.setValue("large");

		assertDoesNotThrow(() -> attributeValidator.validate(attribute));
	}

	@Test
	void shouldThrowWhenAttributeIsNull() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> attributeValidator.validate(null));
		assertEquals("attribute must not be null", ex.getMessage());
	}

	@Test
	void shouldThrowWhenPropertyKeyIsNull() {
		Attribute attribute = new Attribute();
		attribute.setValue("large");

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> attributeValidator.validate(attribute));
		assertEquals("Missing or invalid enum value for field: attribute.name", ex.getMessage());
	}

	@Test
	void shouldThrowWhenValueIsNull() {
		Attribute attribute = new Attribute();
		attribute.setName(PropertyKey.SHOT);

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> attributeValidator.validate(attribute));
		assertEquals("attribute.value must not be null or blank", ex.getMessage());
	}

	@Test
	void shouldThrowWhenValueIsBlank() {
		Attribute attribute = new Attribute();
		attribute.setName(PropertyKey.SHOT);
		attribute.setValue("");

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> attributeValidator.validate(attribute));
		assertEquals("attribute.value must not be null or blank", ex.getMessage());
	}
}
