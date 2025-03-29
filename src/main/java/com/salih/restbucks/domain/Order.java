package com.salih.restbucks.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private String orderId;
	private List<Item> items = new ArrayList<>();
	private ConsumeLocation location;
	private String status;
	private double cost;
	private String currency;
}
