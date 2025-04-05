package com.salih.restbucks.client.api;

import java.util.Map;

import com.salih.restbucks.client.http.RestbucksHttpClient;
import com.salih.restbucks.client.web.pox.xmlmodel.Attribute;
import com.salih.restbucks.client.web.pox.xmlmodel.ConsumeLocation;
import com.salih.restbucks.client.web.pox.xmlmodel.Item;
import com.salih.restbucks.client.web.pox.xmlmodel.Items;
import com.salih.restbucks.client.web.pox.xmlmodel.Order;
import com.salih.restbucks.client.web.pox.xmlmodel.Product;
import com.salih.restbucks.client.web.pox.xmlmodel.ProductType;
import com.salih.restbucks.client.web.pox.xmlmodel.PropertyKey;
import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.common.log.StaticLogger;
import com.salih.restbucks.common.log.helpers.Emoji;

public class RestbucksClientApp implements Loggable {
	public static void main(String[] args) {
		StaticLogger.logger(RestbucksClientApp.class).atInfo().log("{} Starting Restbucks client...", Emoji.ROCKET);

		String response = sendRequest("pox");

		StaticLogger.logger(RestbucksClientApp.class).atInfo().log("{} Received response: {}", Emoji.CHECK, response);
	}

	public static String sendRequest(String commTech) {
		RestbucksHttpClient client = new RestbucksHttpClient();
		if (commTech.equalsIgnoreCase("tunneling")) {
			Map<String, String> attributes = Map.of("shot", "double");

			StaticLogger.logger(RestbucksClientApp.class).atDebug().log("{} Sending order for product='ESPRESSO', quantity=1, attributes={}", Emoji.CART,
					attributes);

			return client.placeOrderUriTunnelling("ESPRESSO", 1, attributes);
		} else if (commTech.equalsIgnoreCase("pox")) {
			Order order = new Order();
			Items items = new Items();

			Item item = new Item();
			item.setQuantity(1);

			Product product = new Product();
			product.setName("ESPRESSO");
			product.setType(ProductType.DRINK);

			Attribute attribute = new Attribute();
			attribute.setName(PropertyKey.SHOT);
			attribute.setValue("double");

			product.getAttribute().add(attribute);
			item.setProduct(product);

			items.getItem().add(item);
			order.setItems(items);

			order.setLocation(ConsumeLocation.TAKE_AWAY);
			order.setCurrency("USD");

			return client.placeOrderPox(order);
		}
		return "";
	}
}
