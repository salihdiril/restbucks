package com.salih.restbucks.server.web.validation.util;

import com.salih.restbucks.server.web.validation.Validator;

public class ValidatorRunner {
	public static <T> void run(Validator<T> validator, T input) {
		XmlValidationUtils.validateNotNull(input, validator.getClass().getSimpleName() + " input");
		validator.validate(input);
	}
}
