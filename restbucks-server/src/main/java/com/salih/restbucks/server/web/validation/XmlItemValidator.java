package com.salih.restbucks.server.web.validation;

import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validatePositive;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.server.web.pox.xmlmodel.Item;
import com.salih.restbucks.server.web.validation.util.ValidatorRunner;

public class XmlItemValidator implements Validator<Item>, Loggable {
	private final XmlProductValidator productValidator;

	public XmlItemValidator(XmlProductValidator productValidator) {
		this.productValidator = productValidator;
	}

	@Override
	public void validate(Item target) {
		logEnter();

		validatePositive(target.getQuantity(), "item.quantity");
		ValidatorRunner.run(productValidator, target.getProduct());

		logExit();
	}
}
