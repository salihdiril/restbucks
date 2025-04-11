package com.salih.restbucks.server.web.validation;

public interface Validator<T> {
	void validate(T target);
}
