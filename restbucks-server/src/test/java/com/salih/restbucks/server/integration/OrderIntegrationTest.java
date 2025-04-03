package com.salih.restbucks.server.integration;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.salih.restbucks.server.RestbucksServerApi;

@SpringBootTest(classes = RestbucksServerApi.class)
@AutoConfigureMockMvc
public class OrderIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldPlaceOrderWhenRequestIsValid() throws Exception {
		mockMvc.perform(post("/order") //
						.param("product", "ESPRESSO") //
						.param("quantity", "2") //
						.param("shot", "double")) //
				.andExpect(status().isOk()) //
				.andExpect(content().string(startsWith("OrderId=")));
	}

	@Test
	void shouldFailWhenProductIsInvalid() throws Exception {
		mockMvc.perform(post("/order") //
						.param("product", "UNKNOWN") //
						.param("quantity", "2") //
						.param("shot", "double")) //
				.andExpect(status().isOk()) //
				.andExpect(content().string(startsWith("Failure")));
	}

	@Test
	void shouldFailWhenProdutIsMissing() throws Exception {
		mockMvc.perform(post("/order") //
						.param("quantity", "2")) //
				.andExpect(status().isOk()) //
				.andExpect(content().string(startsWith("Failure")));
	}

	@Test
	void shouldIgnoreUnknownAttributes() throws Exception {
		mockMvc.perform(post("/order") //
						.param("product", "UNKNOWN") //
						.param("quantity", "2") //
						.param("shot", "double") //
						.param("taste", "acidic")) //
				.andExpect(status().isOk()) //
				.andExpect(content().string(startsWith("Failure")));
	}
}
