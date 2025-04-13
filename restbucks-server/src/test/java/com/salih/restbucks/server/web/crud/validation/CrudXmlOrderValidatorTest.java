package com.salih.restbucks.server.web.crud.validation;

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

import com.salih.restbucks.server.web.crud.xmlmodel.ConsumeLocation;
import com.salih.restbucks.server.web.crud.xmlmodel.Item;
import com.salih.restbucks.server.web.crud.xmlmodel.Items;
import com.salih.restbucks.server.web.crud.xmlmodel.Order;
import com.salih.restbucks.server.web.crud.xmlmodel.Product;

@SpringBootTest
public class CrudXmlOrderValidatorTest {

	@MockitoBean
	private CrudXmlItemValidator itemValidator;
	@Autowired
	private CrudXmlOrderValidator orderValidator;

	@Test
	void shouldValidateValidOrder() {
		Product product1 = mock(Product.class);
		Product product2 = mock(Product.class);

		Item item1 = new Item();
		item1.setProduct(product1);
		item1.setQuantity(2);

		Item item2 = new Item();
		item2.setProduct(product2);
		item2.setQuantity(1);

		Items items = new Items();
		items.getItem().addAll(List.of(item1, item2));

		Order order = new Order();
		order.setLocation(ConsumeLocation.TAKE_AWAY);
		order.setItems(items);
		order.setCurrency("usd");

		assertDoesNotThrow(() -> orderValidator.validate(order));
		verify(itemValidator, times(2)).validate(any(Item.class));
	}

	@Test
	void shouldThrowWhenLocationIsNull() {
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> orderValidator.validate(new Order()));
		assertEquals("Missing or invalid enum value for field: order.location", ex.getMessage());
	}

	@Test
	void shouldThrowWhenItemsListIsNull() {
		Order order = new Order();
		order.setLocation(ConsumeLocation.TAKE_AWAY);

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> orderValidator.validate(order));
		assertEquals("target.items must not be null", ex.getMessage());
	}

	@Test
	void shouldThrowWhenItemListIsEmpty() {
		Order order = new Order();
		order.setLocation(ConsumeLocation.TAKE_AWAY);
		order.setItems(new Items());

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> orderValidator.validate(order));
		assertEquals("target.items.item must contain at least one item.", ex.getMessage());
	}
}
