package com.salih.restbucks.server.web.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.salih.restbucks.server.web.pox.xmlmodel.Attribute;
import com.salih.restbucks.server.web.pox.xmlmodel.PropertyKey;

@SpringBootTest
public class XmlAttributeValidatorTest {

	@Autowired
	private XmlAttributeValidator attributeValidator;

	@Test
	void shouldNotThrowWhenAttributeIsValid() {
		Attribute xmlAttribute = new Attribute();
		xmlAttribute.setName(PropertyKey.SIZE);
		xmlAttribute.setValue("large");

		assertDoesNotThrow(() -> attributeValidator.validate(xmlAttribute));
	}

	@Test
	void shouldThrowWhenAttributeIsNull() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> attributeValidator.validate(null));
		assertEquals("attribute must not be null", ex.getMessage());
	}

	@Test
	void shouldThrowWhenPropertyKeyIsNull() {
		Attribute xmlAttribute = new Attribute();
		xmlAttribute.setValue("large");

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> attributeValidator.validate(xmlAttribute));
		assertEquals("Missing or invalid enum value for field: attribute.name", ex.getMessage());
	}

	@Test
	void shouldThrowWhenValueIsNull() {
		Attribute xmlAttribute = new Attribute();
		xmlAttribute.setName(PropertyKey.SIZE);

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> attributeValidator.validate(xmlAttribute));
		assertEquals("attribute.value must not be null or blank", ex.getMessage());
	}

	@Test
	void shouldThrowWhenValueIsBlank() {
		Attribute xmlAttribute = new Attribute();
		xmlAttribute.setName(PropertyKey.SIZE);
		xmlAttribute.setValue("");

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> attributeValidator.validate(xmlAttribute));
		assertEquals("attribute.value must not be null or blank", ex.getMessage());
	}
}
