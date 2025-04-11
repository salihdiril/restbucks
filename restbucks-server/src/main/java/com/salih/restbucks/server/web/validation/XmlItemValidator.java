package com.salih.restbucks.server.web.validation;

import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validatePositive;

import com.salih.restbucks.server.web.pox.xmlmodel.Item;
import com.salih.restbucks.server.web.validation.util.ValidatorRunner;

public class XmlItemValidator implements Validator<Item> {
	private final XmlProductValidator productValidator = new XmlProductValidator();

	@Override
	public void validate(Item target) {
		validatePositive(target.getQuantity(), "item.quantity");

		ValidatorRunner.run(productValidator, target.getProduct());
	}
}
