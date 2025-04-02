package com.salih.restbucks.client.api;

import java.util.Map;

import com.salih.restbucks.client.http.RestbucksHttpClient;
import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.common.log.StaticLogger;
import com.salih.restbucks.common.log.helpers.Emoji;

public class RestbucksClientApp implements Loggable {
	public static void main(String[] args) {
		StaticLogger.logger(RestbucksClientApp.class).atInfo().log("{} Starting Restbucks client...", Emoji.ROCKET);

		RestbucksHttpClient client = new RestbucksHttpClient();
		Map<String, String> attributes = Map.of("shot", "double");

		StaticLogger.logger(RestbucksClientApp.class).atDebug().log("{} Sending order for product='ESPRESSO', quantity=1, attributes={}", Emoji.CART,
				attributes);

		String response = client.placeOrder("ESPRESSO", 1, attributes);

		StaticLogger.logger(RestbucksClientApp.class).atInfo().log("{} Received response: {}", Emoji.CHECK, response);
	}
}
