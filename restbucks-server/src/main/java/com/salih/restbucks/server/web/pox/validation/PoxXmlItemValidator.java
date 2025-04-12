package com.salih.restbucks.server.web.pox.validation;

import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validatePositive;

import org.springframework.stereotype.Component;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.server.web.pox.xmlmodel.Item;
import com.salih.restbucks.server.web.validation.Validator;
import com.salih.restbucks.server.web.validation.util.ValidatorRunner;

@Component
public class PoxXmlItemValidator implements Validator<Item>, Loggable {
	private final PoxXmlProductValidator productValidator;

	public PoxXmlItemValidator(PoxXmlProductValidator productValidator) {
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
