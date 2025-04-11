package com.salih.restbucks.server.web.validation;

import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateEnumNotNull;
import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateNotNull;
import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateStringNotBlank;

import com.salih.restbucks.server.web.pox.xmlmodel.Attribute;

public class XmlAttributeValidator implements Validator<Attribute> {
	@Override
	public void validate(Attribute target) {
		validateNotNull(target, "attribute");
		validateEnumNotNull(target.getName(), "attribute.name");
		validateStringNotBlank(target.getValue(), "attribute.value");
	}
}
