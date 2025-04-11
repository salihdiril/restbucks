package com.salih.restbucks.server.web.validation;

import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateEnumNotNull;
import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateListNotEmpty;
import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateNotNull;

import com.salih.restbucks.server.web.pox.xmlmodel.Order;
import com.salih.restbucks.server.web.validation.util.ValidatorRunner;

public class XmlOrderValidator implements Validator<Order> {
	private final XmlItemValidator itemValidator = new XmlItemValidator();

	@Override
	public void validate(Order target) {
		validateEnumNotNull(target.getLocation(), "order.location");
		validateNotNull(target.getItems(), "target.items");
		validateListNotEmpty(target.getItems().getItem(), "target.items.item");

		target.getItems().getItem().forEach(item -> ValidatorRunner.run(itemValidator, item));
	}
}
