package com.salih.restbucks.server.web.crud.validation;

import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateEnumNotNull;
import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateNotNull;
import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateStringNotBlank;

import org.springframework.stereotype.Component;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.server.web.crud.xmlmodel.Attribute;
import com.salih.restbucks.server.web.validation.Validator;

@Component
public class CrudXmlAttributeValidator implements Validator<Attribute>, Loggable {
	@Override
	public void validate(Attribute target) {
		logEnter();

		validateNotNull(target, "attribute");
		validateEnumNotNull(target.getName(), "attribute.name");
		validateStringNotBlank(target.getValue(), "attribute.value");

		logExit();
	}
}
