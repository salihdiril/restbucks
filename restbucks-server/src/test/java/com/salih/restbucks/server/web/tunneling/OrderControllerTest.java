package com.salih.restbucks.server.web.tunneling;

import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.salih.restbucks.server.config.MockConfig;
import com.salih.restbucks.server.domain.Product;
import com.salih.restbucks.server.domain.ProductType;
import com.salih.restbucks.server.service.ProductCatalog;

@WebMvcTest(OrderControllerTest.class)
@ActiveProfiles("test-mock")
@Import(MockConfig.class)
public class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductCatalog productCatalog;

	private final Product dummyProduct = new Product("ESPRESSO", ProductType.DRINK);

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
