package com.salih.restbucks.common.log.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonStringSerializer {

	private static final ObjectMapper mapper = new ObjectMapper();

	private JsonStringSerializer() {
	}

	public static String toJsonString(Object obj) {
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Failed to serialize object to JSON", e);
		}
	}
}
