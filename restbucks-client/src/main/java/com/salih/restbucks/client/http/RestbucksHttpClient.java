package com.salih.restbucks.client.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Map.Entry;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.common.log.helpers.Emoji;

public class RestbucksHttpClient implements Loggable {
	public String placeOrder(String product, int quantity, Map<String, String> attributes) {
		logEnter();

		try {
			StringBuilder query = new StringBuilder("product=" + product + "&quantity=" + quantity);
			for (Entry<String, String> entry : attributes.entrySet()) {
				query.append("&").append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
			}

			URL url = new URL("http://localhost:8080/order?" + query);
			logger().atDebug().log("{} Sending POST request to: {}", Emoji.WORLD, url);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				final String response = reader.readLine();
				logger().atInfo().log("{} Received response from server: {}", Emoji.CHECK, response);
				logExit();
				return response;
			}
		} catch (IOException e) {
			logger().atError().log("{} Failed to place order (product={}, quantity={}, attributes={}): {}",
					Emoji.ERROR, product, quantity, attributes, e.getMessage());
			logExit();
			return null;
		}
	}
}
