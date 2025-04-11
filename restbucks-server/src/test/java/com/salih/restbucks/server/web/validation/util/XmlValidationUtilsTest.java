package com.salih.restbucks.server.web.validation.util;

import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateEnumNotNull;
import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateListNotEmpty;
import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateNotNull;
import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validatePositive;
import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateStringNotBlank;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.salih.restbucks.server.domain.PropertyKey;

public class XmlValidationUtilsTest {

	@Test
	void shouldNotThrowForValidEnum() {
		assertDoesNotThrow(() -> validateEnumNotNull(PropertyKey.MILK, "property"));
	}

	@Test
	void shouldThrowForNullEnum() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> validateEnumNotNull(null, "property"));
		assertEquals("Missing or invalid enum value for field: property", ex.getMessage());
	}

	@Test
	void shouldNotThrowForNonNullObject() {
		assertDoesNotThrow(() -> validateNotNull("hello", "property"));
	}

	@Test
	void shouldThrowForNullObject() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> validateNotNull(null, "greeting"));
		assertEquals("greeting must not be null", ex.getMessage());
	}

	@Test
	void shouldNotThrowForNonEmptyList() {
		assertDoesNotThrow(() -> validateListNotEmpty(List.of("item"), "items"));
	}

	@Test
	void shouldThrowForNullList() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> validateListNotEmpty(null, "items"));
		assertEquals("items must contain at least one item.", ex.getMessage());
	}

	@Test
	void shouldThrowForEmptyList() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> validateListNotEmpty(List.of(), "items"));
		assertEquals("items must contain at least one item.", ex.getMessage());
	}

	@Test
	void shouldNotThrowForPositiveInteger() {
		assertDoesNotThrow(() -> validatePositive(1, "quantity"));
	}

	@Test
	void shouldThrowForZero() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> validatePositive(0, "quantity"));
		assertEquals("quantity must be a positive number.", ex.getMessage());
	}

	@Test
	void shouldThrowForNegativeInteger() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> validatePositive(-1, "quantity"));
		assertEquals("quantity must be a positive number.", ex.getMessage());
	}

	@Test
	void shouldNotThrowForNonBlankString() {
		assertDoesNotThrow(() -> validateStringNotBlank("hello", "field"));
	}

	@Test
	void shouldThrowForNullString() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> validateStringNotBlank(null, "field"));
		assertEquals("field must not be null or blank", ex.getMessage());
	}

	@Test
	void shouldThrowForBlankString() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> validateStringNotBlank("", "field"));
		assertEquals("field must not be null or blank", ex.getMessage());
	}
}
