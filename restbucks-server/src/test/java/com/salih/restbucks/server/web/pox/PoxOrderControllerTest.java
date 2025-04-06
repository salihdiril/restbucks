package com.salih.restbucks.server.web.pox;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PoxOrderController.class)
public class PoxOrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private static final String SAMPLE_XML = """
			<order xmlns="http://restbucks.salih.com/schema"
			       xmlns:c="http://restbucks.salih.com/schema/common">
			    <items>
			        <item>
			            <product>
			                <name>Latte</name>
			                <type>DRINK</type>
			                <c:attribute>
			                    <c:name>SIZE</c:name>
			                    <c:value>Large</c:value>
			                </c:attribute>
			            </product>
			            <quantity>2</quantity>
			        </item>
			    </items>
			    <location>IN_SHOP</location>
			    <currency>USD</currency>
			</order>
			""";

	@Test
	void shouldAcceptXmlOrderAndReturnConfirmation() throws Exception {
		mockMvc.perform(post("/pox/order") //
						.contentType(MediaType.APPLICATION_XML) //
						.accept(MediaType.APPLICATION_XML) //
						.content(SAMPLE_XML)) //
				.andExpect(status().isCreated()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML)) //
				.andExpect(xpath("/orderConfirmation/orderId").exists());
	}
}
