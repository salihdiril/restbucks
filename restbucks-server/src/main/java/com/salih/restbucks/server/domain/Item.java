package com.salih.restbucks.server.domain;

import java.util.List;

public class Item {
	private Product product;
	private int quantity;
	private List<Attribute> attributes;

	public Item(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Item setProduct(Product product) {
		this.product = product;
		return this;
	}

	public Item setQuantity(int quantity) {
		this.quantity = quantity;
		return this;
	}

	public Item setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
		return this;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}
}
