package com.salih.restbucks.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salih.restbucks.domain.Order;
import com.salih.restbucks.service.ProductCatalog;
import com.salih.restbucks.web.util.OrderUriParser;

@RestController
@RequestMapping("/order")
public class OrderController {
	private final ProductCatalog productCatalog;

	@Autowired
	public OrderController(ProductCatalog productCatalog) {
		this.productCatalog = productCatalog;
	}

	@PostMapping
	public String placeOrderViaUri(@RequestParam Map<String, String> params) {
		try {
			Order order = OrderUriParser.parse(params, productCatalog);
			if (order != null) {
				return "OrderId=" + order.getOrderId();
			} else {
				return "Failure: Could not place order.";
			}
		} catch (Exception e) {
			return "Failure: " + e.getMessage();
		}
	}
}
