package com.salih.restbucks.server.web.validation.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import com.salih.restbucks.server.web.validation.Validator;

public class ValidatorRunnerTest {

	@Test
	void shouldRunValidatorWhenInputIsNotNull() {
		@SuppressWarnings("unchecked") Validator<String> validator = mock(Validator.class);
		String input = "valid";

		ValidatorRunner.run(validator, input);

		verify(validator).validate(input);
	}

	@Test
	void shouldThrowExceptionWhenInputIsNull() {
		@SuppressWarnings("unchecked") Validator<Object> validator = mock(Validator.class);

		IllegalArgumentException exception = assertThrows( //
				IllegalArgumentException.class, //
				() -> ValidatorRunner.run(validator, null) //
		);

		assertEquals(validator.getClass().getSimpleName() + " input must not be null", exception.getMessage());
	}
}
