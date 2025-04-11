package com.salih.restbucks.server.web.validation;

import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateEnumNotNull;
import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateNotNull;
import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateStringNotBlank;

import org.springframework.stereotype.Component;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.server.web.pox.xmlmodel.Attribute;

@Component
public class XmlAttributeValidator implements Validator<Attribute>, Loggable {
	@Override
	public void validate(Attribute target) {
		logEnter();

		validateNotNull(target, "attribute");
		validateEnumNotNull(target.getName(), "attribute.name");
		validateStringNotBlank(target.getValue(), "attribute.value");

		logExit();
	}
}
