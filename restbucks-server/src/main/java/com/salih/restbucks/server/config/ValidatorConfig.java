package com.salih.restbucks.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.salih.restbucks.server.web.validation.XmlAttributeValidator;
import com.salih.restbucks.server.web.validation.XmlItemValidator;
import com.salih.restbucks.server.web.validation.XmlOrderValidator;
import com.salih.restbucks.server.web.validation.XmlProductValidator;

@Configuration
public class ValidatorConfig {

	@Bean
	public XmlAttributeValidator xmlAttributeValidator() {
		return new XmlAttributeValidator();
	}

	@Bean
	public XmlProductValidator xmlProductValidator(XmlAttributeValidator attributeValidator) {
		return new XmlProductValidator(attributeValidator);
	}

	@Bean
	public XmlItemValidator xmlItemValidator(XmlProductValidator productValidator) {
		return new XmlItemValidator(productValidator);
	}

	@Bean
	public XmlOrderValidator xmlOrderValidator(XmlItemValidator itemValidator) {
		return new XmlOrderValidator(itemValidator);
	}
}
