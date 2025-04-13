package com.salih.restbucks.server.web.crud.validation;

import static com.salih.restbucks.server.web.validation.util.XmlValidationUtils.validateEnumNotNull;

import org.springframework.stereotype.Component;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.server.web.crud.xmlmodel.Product;
import com.salih.restbucks.server.web.validation.Validator;

@Component
public class CrudXmlProductValidator implements Validator<Product>, Loggable {
	@Override
	public void validate(Product target) {
		logEnter();

		validateEnumNotNull(target.getType(), "product.type");

		logExit();
	}
}
