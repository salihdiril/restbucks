package com.salih.restbucks.server.web.validation;

import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateEnumNotNull;

import com.salih.restbucks.server.web.pox.xmlmodel.Product;
import com.salih.restbucks.server.web.validation.util.ValidatorRunner;

public class XmlProductValidator implements Validator<Product> {
	private final XmlAttributeValidator attributeValidator = new XmlAttributeValidator();

	@Override
	public void validate(Product target) {
		validateEnumNotNull(target.getType(), "product.type");

		if (target.getAttribute() != null) {
			target.getAttribute().forEach(attribute -> ValidatorRunner.run(attributeValidator, attribute));
		}
	}
}
