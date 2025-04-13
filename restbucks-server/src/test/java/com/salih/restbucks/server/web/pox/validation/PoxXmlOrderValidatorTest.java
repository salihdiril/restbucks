package com.salih.restbucks.server.web.pox.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.salih.restbucks.server.web.pox.xmlmodel.ConsumeLocation;
import com.salih.restbucks.server.web.pox.xmlmodel.Item;
import com.salih.restbucks.server.web.pox.xmlmodel.Items;
import com.salih.restbucks.server.web.pox.xmlmodel.Order;
import com.salih.restbucks.server.web.pox.xmlmodel.Product;

@SpringBootTest
public class PoxXmlOrderValidatorTest {

	@MockitoBean
	private PoxXmlItemValidator itemValidator;
	@Autowired
	private PoxXmlOrderValidator orderValidator;

	@Test
	void shouldValidateValidOrder() {
		Product xmlProduct1 = mock(Product.class);
		Product xmlProduct2 = mock(Product.class);

		Item xmlItem1 = new Item();
		xmlItem1.setProduct(xmlProduct1);
		xmlItem1.setQuantity(2);

		Item xmlItem2 = new Item();
		xmlItem1.setProduct(xmlProduct2);
		xmlItem1.setQuantity(1);

		Items xmlItems = new Items();
		xmlItems.getItem().addAll(List.of(xmlItem1, xmlItem2));

		Order xmlOrder = new Order();
		xmlOrder.setItems(xmlItems);
		xmlOrder.setLocation(ConsumeLocation.IN_SHOP);
		xmlOrder.setCurrency("USD");

		assertDoesNotThrow(() -> orderValidator.validate(xmlOrder));

		verify(itemValidator, times(2)).validate(any(Item.class));
	}

	@Test
	void shouldThrowWhenLocationIsNull() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> orderValidator.validate(new Order()));
		assertEquals("Missing or invalid enum value for field: order.location", ex.getMessage());
	}

	@Test
	void shouldThrowWhenItemsListIsNull() {
		Order xmlOrder = new Order();
		xmlOrder.setLocation(ConsumeLocation.TAKE_AWAY);

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> orderValidator.validate(xmlOrder));
		assertEquals("target.items must not be null", ex.getMessage());
	}

	@Test
	void shouldThrowWhenItemListIsEmpty() {
		Order xmlOrder = new Order();
		xmlOrder.setLocation(ConsumeLocation.TAKE_AWAY);
		xmlOrder.setItems(new Items());

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> orderValidator.validate(xmlOrder));
		assertEquals("target.items.item must contain at least one item.", ex.getMessage());
	}
}
