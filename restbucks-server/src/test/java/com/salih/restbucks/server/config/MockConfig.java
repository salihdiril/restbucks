package com.salih.restbucks.server.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.salih.restbucks.server.service.ProductCatalog;

@TestConfiguration
@Profile("test-mock")
public class MockConfig {
	@Bean
	public ProductCatalog productCatalog() {
		return Mockito.mock(ProductCatalog.class);
	}
}
