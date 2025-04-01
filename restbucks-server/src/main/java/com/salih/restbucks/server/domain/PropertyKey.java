package com.salih.restbucks.server.domain;

public enum PropertyKey {
	MILK("milk"),
	SIZE("size"),
	WHIPPED_CREAM("whippedCream"),
	SHOT("shot"),
	KIND("kind");

	private final String key;

	PropertyKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}
