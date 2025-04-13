package com.salih.restbucks.server.web.crud.controller;

import java.rmi.UnmarshalException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.server.web.crud.mapper.CrudOrderMapper;
import com.salih.restbucks.server.web.crud.validation.CrudXmlOrderValidator;
import com.salih.restbucks.server.web.crud.xmlmodel.Order;
import com.salih.restbucks.server.web.validation.util.ValidatorRunner;

@RestController
@RequestMapping(path = "/orders", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
public class CrudOrderController implements Loggable {

	private final CrudXmlOrderValidator orderValidator;
	private final CrudOrderMapper orderMapper;

	@Autowired
	public CrudOrderController(CrudXmlOrderValidator orderValidator, CrudOrderMapper orderMapper) {
		this.orderValidator = orderValidator;
		this.orderMapper = orderMapper;
	}

	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody com.salih.restbucks.server.web.crud.xmlmodel.Order xmlOrder) {
		ValidatorRunner.run(orderValidator, xmlOrder);

		com.salih.restbucks.server.domain.Order domainOrder = orderMapper.toDomain(xmlOrder);

		Order responseXmlOrder = orderMapper.toXml(domainOrder);

		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_XML).body(responseXmlOrder);
	}

	@ExceptionHandler(java.rmi.UnmarshalException.class)
	public ResponseEntity<String> handleInvalidXml(UnmarshalException ex) {
		logger().atWarn().log("Invalid XML received: {}", ex.getMessage());
		return ResponseEntity.badRequest().body("Malformed XML payload.");
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
		logger().atWarn().log("invalid input: {}", ex.getMessage());
		return ResponseEntity.badRequest().body("Invalid values in XML payload.");
	}

}
