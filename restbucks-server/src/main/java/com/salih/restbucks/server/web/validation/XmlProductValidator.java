package com.salih.restbucks.server.web.validation;

import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateEnumNotNull;
import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateNotNull;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.server.web.pox.xmlmodel.Product;
import com.salih.restbucks.server.web.validation.util.ValidatorRunner;

public class XmlProductValidator implements Validator<Product>, Loggable {
	private final XmlAttributeValidator attributeValidator;

	public XmlProductValidator(XmlAttributeValidator attributeValidator) {
		this.attributeValidator = attributeValidator;
	}

	@Override
	public void validate(Product target) {
		logEnter();

		validateEnumNotNull(target.getType(), "product.type");
		validateNotNull(target.getAttribute(), "product.attribute");

		target.getAttribute().forEach(attribute -> ValidatorRunner.run(attributeValidator, attribute));

		logExit();
	}
}
