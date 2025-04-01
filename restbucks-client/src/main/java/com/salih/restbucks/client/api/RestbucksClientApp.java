package com.salih.restbucks.client.api;

import java.util.Map;

import com.salih.restbucks.client.http.RestbucksHttpClient;

public class RestbucksClientApp {
	public static void main(String[] args) {
		RestbucksHttpClient client = new RestbucksHttpClient();

		Map<String, String> attributes = Map.of("shot", "double");
		String response = client.placeOrder("ESPRESSO", 1, attributes);

		System.out.println("🧾 Response: " + response);
	}
}
