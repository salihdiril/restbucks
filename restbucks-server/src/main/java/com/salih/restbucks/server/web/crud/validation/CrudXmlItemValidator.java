package com.salih.restbucks.server.web.crud.validation;

import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validatePositive;

import org.springframework.stereotype.Component;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.server.web.crud.xmlmodel.Item;
import com.salih.restbucks.server.web.validation.Validator;
import com.salih.restbucks.server.web.validation.util.ValidatorRunner;

@Component
public class CrudXmlItemValidator implements Validator<Item>, Loggable {
	private final CrudXmlProductValidator productValidator;
	private final CrudXmlAttributeValidator attributeValidator;

	public CrudXmlItemValidator(CrudXmlProductValidator productValidator, CrudXmlAttributeValidator attributeValidator) {
		this.productValidator = productValidator;
		this.attributeValidator = attributeValidator;
	}

	@Override
	public void validate(Item target) {
		logEnter();

		validatePositive(target.getQuantity(), "item.quantity");
		ValidatorRunner.run(productValidator, target.getProduct());
		target.getAttributes().getAttribute().forEach(attribute -> ValidatorRunner.run(attributeValidator, attribute));

		logExit();
	}
}
