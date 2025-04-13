package com.salih.restbucks.server.web.mapper;

import com.salih.restbucks.server.domain.Order;

@FunctionalInterface
public interface ToXmlMapper<OUT> {
	OUT toXml(Order order);
}
