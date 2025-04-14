package com.salih.restbucks.server.web.crud.mapper;

import static com.salih.restbucks.server.web.testutil.TestXmlOrderFactory.createCrudXmlOrderWithMissingAttribute;
import static com.salih.restbucks.server.web.testutil.TestXmlOrderFactory.createValidCrudXmlOrder;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.salih.restbucks.server.domain.ConsumeLocation;
import com.salih.restbucks.server.domain.Item;
import com.salih.restbucks.server.web.crud.xmlmodel.Order;

@SpringBootTest
public class CrudOrderMapperTest {

	@Autowired
	private CrudOrderMapper orderMapper;

	@Test
	void shouldMapXmlOrderToDomainOrderCorrectly() {
		Order xmlOrder = createValidCrudXmlOrder();

		com.salih.restbucks.server.domain.Order domainOrder = orderMapper.toDomain(xmlOrder);

		assertAll("Mapped Domain Order", //
				() -> assertNotNull(domainOrder), //
				() -> assertEquals(1, domainOrder.getItems().size()), //
				() -> assertEquals("usd", domainOrder.getCurrency()), //
				() -> assertEquals(ConsumeLocation.TAKE_AWAY, domainOrder.getLocation()));

		com.salih.restbucks.server.domain.Item domainItem = domainOrder.getItems().get(0);

		assertAll("Mapped Item", //
				() -> assertNotNull(domainItem), //
				() -> assertEquals(1, domainItem.getQuantity()), //
				() -> assertEquals(com.salih.restbucks.server.domain.ProductType.DRINK, domainItem.getProduct().type()), //
				() -> assertEquals("ESPRESSO", domainItem.getProduct().name()), //
				() -> assertEquals(1, domainItem.getAttributes().size()));

		com.salih.restbucks.server.domain.Attribute domainAttribute = domainItem.getAttributes().get(0);

		assertAll("Mapped Attributes", //
				() -> assertNotNull(domainAttribute), //
				() -> assertEquals(com.salih.restbucks.server.domain.PropertyKey.SHOT, domainAttribute.propertyKey()), //
				() -> assertEquals("double", domainAttribute.value()));
	}

	@Test
	void shouldMapItemWithNoAttributesToDomainOrderCorrectly() {
		Order xmlOrder = createCrudXmlOrderWithMissingAttribute();

		com.salih.restbucks.server.domain.Order domainOrder = orderMapper.toDomain(xmlOrder);

		assertNotNull(domainOrder);
		assertEquals(1, domainOrder.getItems().size());

		Item domainItem = domainOrder.getItems().get(0);
		assertNotNull(domainItem);
		assertEquals(List.of(), domainItem.getAttributes());
	}
}
