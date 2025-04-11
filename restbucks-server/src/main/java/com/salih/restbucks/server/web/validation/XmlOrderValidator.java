package com.salih.restbucks.server.web.validation;

import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateEnumNotNull;
import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateListNotEmpty;
import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateNotNull;

import org.springframework.stereotype.Component;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.server.web.pox.xmlmodel.Order;
import com.salih.restbucks.server.web.validation.util.ValidatorRunner;

@Component
public class XmlOrderValidator implements Validator<Order>, Loggable {
	private final XmlItemValidator itemValidator;

	public XmlOrderValidator(XmlItemValidator itemValidator) {
		this.itemValidator = itemValidator;
	}

	@Override
	public void validate(Order target) {
		logEnter();

		validateEnumNotNull(target.getLocation(), "order.location");
		validateNotNull(target.getItems(), "target.items");
		validateListNotEmpty(target.getItems().getItem(), "target.items.item");

		target.getItems().getItem().forEach(item -> ValidatorRunner.run(itemValidator, item));

		logExit();
	}
}
