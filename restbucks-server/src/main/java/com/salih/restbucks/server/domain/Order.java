package com.salih.restbucks.server.domain;

import java.util.List;

public class Order {
	private final String orderId;
	private List<Item> items;
	private ConsumeLocation location;
	private String currency;
	private OrderStatus status;
	private double cost;

	public Order(String orderId, List<Item> items, ConsumeLocation location, String currency) {
		this.orderId = orderId;
		this.items = items;
		this.location = location;
		this.currency = currency;
	}

	public Order setItems(List<Item> items) {
		this.items = items;
		return this;
	}

	public Order setLocation(ConsumeLocation location) {
		this.location = location;
		return this;
	}

	public Order setCurrency(String currency) {
		this.currency = currency;
		return this;
	}

	public Order setStatus(OrderStatus status) {
		this.status = status;
		return this;
	}

	public Order setCost(double cost) {
		this.cost = cost;
		return this;
	}

	public String getOrderId() {
		return orderId;
	}

	public List<Item> getItems() {
		return items;
	}

	public ConsumeLocation getLocation() {
		return location;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public double getCost() {
		return cost;
	}

	public String getCurrency() {
		return currency;
	}
}
