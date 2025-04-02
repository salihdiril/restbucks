package com.salih.restbucks.common.exception.domain;

public class ProductNotFoundException extends RuntimeException {
	public ProductNotFoundException(String productName) {
		super("Product not found: " + productName);
	}
}
