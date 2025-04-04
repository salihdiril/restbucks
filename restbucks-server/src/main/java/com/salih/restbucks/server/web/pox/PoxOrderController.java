package com.salih.restbucks.server.web.pox;

import java.rmi.UnmarshalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salih.restbucks.common.log.Loggable;
import com.salih.restbucks.server.web.pox.mapper.OrderConfirmationMapper;
import com.salih.restbucks.server.web.pox.mapper.XmlOrderMapper;
import com.salih.restbucks.server.web.pox.xmlmodel.Order;
import com.salih.restbucks.server.web.pox.xmlmodel.OrderConfirmation;

@RestController
@RequestMapping("/pox/order")
public class PoxOrderController implements Loggable {

	@PostMapping( //
			consumes = MediaType.APPLICATION_XML_VALUE, //
			produces = MediaType.APPLICATION_XML_VALUE //
	)
	public ResponseEntity<OrderConfirmation> createOrder(@RequestBody Order xmlOrder) {
		logger().atInfo().log("Received POX order with currency: {}", xmlOrder.getCurrency());

		com.salih.restbucks.server.domain.Order domainOrder = XmlOrderMapper.map(xmlOrder);

		OrderConfirmation response = OrderConfirmationMapper.map(domainOrder);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@ExceptionHandler(UnmarshalException.class)
	public ResponseEntity<String> handleInvalidXml(UnmarshalException ex) {
		logger().atWarn().log("Invalid XML received: {}", ex.getMessage());
		return ResponseEntity.badRequest().body("Malformed XML payload.");
	}

}
