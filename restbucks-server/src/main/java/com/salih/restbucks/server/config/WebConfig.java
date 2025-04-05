package com.salih.restbucks.server.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// Remove Jackson XML converter if present
		converters.removeIf(c -> c.getClass().getName().contains("MappingJackson2XmlHttpMessageConverter"));

		// Add JAXB converter
		converters.add(new Jaxb2RootElementHttpMessageConverter());
	}
}
