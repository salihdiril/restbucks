package com.salih.restbucks.server.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.startsWith;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.salih.restbucks.server.domain.Product;
import com.salih.restbucks.server.domain.ProductType;
import com.salih.restbucks.server.service.ProductCatalog;

@WebMvcTest(OrderControllerTest.class)
@Import(OrderControllerTest.MockConfig.class)
public class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private static ProductCatalog productCatalog;

	@TestConfiguration
	static class MockConfig {

		@Bean
		ProductCatalog productCatalog() {
			return OrderControllerTest.productCatalog;
		}
	}

	private final Product dummyProduct = new Product("ESPRESSO", ProductType.DRINK, List.of());

	@BeforeEach
	void setUp() {
		when(productCatalog.findByName("ESPRESSO")).thenReturn(dummyProduct);
	}

	@Test
	void shouldReturnOrderIdWhenOrderIsParsedSuccessfully() throws Exception {
		mockMvc.perform(post("/order") //
						.param("product", "ESPRESSO") //
						.param("quantity", "2") //
						.param("shot", "double")) //
				.andExpect(status().isOk()) //
				.andExpect(content().string(startsWith("OrderId=")));
	}

	@Test
	void shouldReturnFailureWhenProductIsMissing() throws Exception {
		mockMvc.perform(post("/order") //
						.param("quantity", "1")) //
				.andExpect(status().isOk()) //
				.andExpect(content().string(startsWith("Failure:")));
	}

	@Test
	void shouldReturnFailureOnInvalidQuantity() throws Exception {
		mockMvc.perform(post("/order") //
						.param("product", "ESPRESSO") //
						.param("quantity", "two")) //
				.andExpect(status().isOk()) //
				.andExpect(content().string(startsWith("Failure:")));
	}
}
