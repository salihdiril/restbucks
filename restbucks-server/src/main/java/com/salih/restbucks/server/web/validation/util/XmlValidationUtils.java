package com.salih.restbucks.server.web.validation.util;

import java.util.List;

public enum XmlValidationUtils {
	;
	public static <T extends Enum<T>> void validateEnumNotNull(T value, String fieldName) {
		if (value == null) {
			throw new IllegalArgumentException("Missing or invalid enum value for field: " + fieldName);
		}
	}

	public static <T> void validateNotNull(T obj, String fieldName) {
		if (obj == null) {
			throw new IllegalArgumentException(fieldName + " must not be null");
		}
	}

	public static <T> void validateListNotEmpty(List<T> list, String fieldName) {
		if (list == null || list.isEmpty()) {
			throw new IllegalArgumentException(fieldName + " must contain at least one item.");
		}
	}

	public static void validatePositive(int value, String fieldName) {
		if (value <= 0) {
			throw new IllegalArgumentException(fieldName + " must be a positive number.");
		}
	}

	public static void validateStringNotBlank(String value, String fieldName) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException(fieldName + " must not be null or blank");
		}
	}
}
