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

public class RestbucksHttpClient {
	public String placeOrder(String product, int quantity, Map<String, String> attributes) {
		try {
			StringBuilder query = new StringBuilder("product=" + product + "&quantity=" + quantity);
			for (Entry<String, String> entry : attributes.entrySet()) {
				query.append("&").append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
			}

			URL url = new URL("http://localhost:8080/order?" + query);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				return reader.readLine();
			}
		} catch (IOException e) {
			System.err.println("❌ Failed to place order: " + e.getMessage());
			return null;
		}
	}
}
