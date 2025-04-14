package com.salih.restbucks.server.web.pox.mapper;

import static com.salih.restbucks.server.web.testutil.TestXmlOrderFactory.createPoxXmlOrderWithMissingAttribute;
import static com.salih.restbucks.server.web.testutil.TestXmlOrderFactory.createValidPoxXmlOrder;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.salih.restbucks.server.domain.OrderStatus;
import com.salih.restbucks.server.web.pox.xmlmodel.Order;

@SpringBootTest
public class PoxOrderMapperTest {

	@Autowired
	private PoxOrderMapper orderMapper;

	@Test
	void shouldMapXmlOrderToDomainOrderCorrectly() {
		Order xmlOrder = createValidPoxXmlOrder();
		com.salih.restbucks.server.domain.Order domainOrder = orderMapper.toDomain(xmlOrder);

		assertAll("Mapped Domain Order", //
				() -> assertNotNull(domainOrder), //
				() -> assertEquals(1, domainOrder.getItems().size()), //
				() -> assertEquals("USD", domainOrder.getCurrency()), //
				() -> assertEquals(OrderStatus.PENDING, domainOrder.getStatus()), //
				() -> assertEquals(com.salih.restbucks.server.domain.ConsumeLocation.IN_SHOP, domainOrder.getLocation()));

		com.salih.restbucks.server.domain.Item domainItem = domainOrder.getItems().get(0);

		assertAll("Mapped Item", //
				() -> assertEquals("Latte", domainItem.getProduct().name()), //
				() -> assertEquals(com.salih.restbucks.server.domain.ProductType.DRINK, domainItem.getProduct().type()), //
				() -> assertEquals(2, domainItem.getQuantity()), //
				() -> assertEquals(1, domainItem.getAttributes().size()), //
				() -> assertEquals(com.salih.restbucks.server.domain.PropertyKey.SIZE, domainItem.getAttributes().get(0).propertyKey()), //
				() -> assertEquals("Large", domainItem.getAttributes().get(0).value()));
	}

	@Test
	void shouldMapItemWithNoAttributes() {
		var xmlOrder = createPoxXmlOrderWithMissingAttribute();
		var domainOrder = orderMapper.toDomain(xmlOrder);

		assertAll("Mapped Domain Order Without Attributes", //
				() -> assertNotNull(domainOrder), //
				() -> assertEquals(1, domainOrder.getItems().size()), //
				() -> assertEquals(0, domainOrder.getItems().get(0).getAttributes().size()));
	}
}
