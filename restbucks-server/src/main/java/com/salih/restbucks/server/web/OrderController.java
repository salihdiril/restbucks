package com.salih.restbucks.server.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.server.domain.Order;
import com.salih.restbucks.server.service.ProductCatalog;
import com.salih.restbucks.server.web.util.OrderUriParser;

@RestController
@RequestMapping("/order")
public class OrderController implements Loggable {
	private final ProductCatalog productCatalog;

	@Autowired
	public OrderController(ProductCatalog productCatalog) {
		this.productCatalog = productCatalog;
	}

	@PostMapping
	public String placeOrderViaUri(@RequestParam Map<String, String> params) {
		logger().atDebug().log("Parsing order from URI params: {}", params);

		try {
			Order order = OrderUriParser.parse(params, productCatalog);

			if (order != null) {
				logger().atInfo().log("Order placed successfully. ID: {}", order.getOrderId());
				return "OrderId=" + order.getOrderId();
			} else {
				logger().atWarn().log("Failed to place order. Parsed order was null.");
				return "Failure: Could not place order.";
			}

		} catch (Exception e) {
			logger().atError().log("Exception while placing order: {}", e.getMessage());
			return "Failure: " + e.getMessage();
		}
	}
}
