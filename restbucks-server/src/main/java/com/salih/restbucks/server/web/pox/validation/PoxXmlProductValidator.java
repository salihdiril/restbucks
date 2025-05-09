package com.salih.restbucks.server.web.pox.validation;

import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateEnumNotNull;

import org.springframework.stereotype.Component;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.server.web.pox.xmlmodel.Product;
import com.salih.restbucks.server.web.validation.Validator;
import com.salih.restbucks.server.web.validation.util.ValidatorRunner;

@Component
public class PoxXmlProductValidator implements Validator<Product>, Loggable {
	private final PoxXmlAttributeValidator attributeValidator;

	public PoxXmlProductValidator(PoxXmlAttributeValidator attributeValidator) {
		this.attributeValidator = attributeValidator;
	}

	@Override
	public void validate(Product target) {
		logEnter();

		validateEnumNotNull(target.getType(), "product.type");

		target.getAttribute().forEach(attribute -> ValidatorRunner.run(attributeValidator, attribute));

		logExit();
	}
}
