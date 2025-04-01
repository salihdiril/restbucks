package com.salih.restbucks.server.domain;

import java.util.List;

public class Order {
	private String orderId;
	private List<Item> items;
	private ConsumeLocation location;
	private OrderStatus status;
	private double cost;
	private String currency;

	public Order(String orderId, List<Item> items, ConsumeLocation location, OrderStatus status, double cost, String currency) {
		this.orderId = orderId;
		this.items = items;
		this.location = location;
		this.status = status;
		this.cost = cost;
		this.currency = currency;
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
