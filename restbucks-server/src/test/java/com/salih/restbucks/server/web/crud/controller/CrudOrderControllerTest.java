package com.salih.restbucks.server.web.crud.controller;

import static com.salih.restbucks.server.web.testutil.OrderXmlSamples.INVALID_XML_CRUD;
import static com.salih.restbucks.server.web.testutil.OrderXmlSamples.MISSING_LOCATION_XML_CRUD;
import static com.salih.restbucks.server.web.testutil.OrderXmlSamples.SAMPLE_XML_CRUD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CrudOrderController.class)
public class CrudOrderControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldAcceptXmlOrderAndReturnEnrichedXmlOrder() throws Exception {
		mockMvc.perform(post("/orders") //
						.contentType(MediaType.APPLICATION_XML) //
						.accept(MediaType.APPLICATION_XML) //
						.content(SAMPLE_XML_CRUD)) //
				.andExpect(status().isCreated()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML)) //
				.andExpect(xpath("/order/id").exists());
	}

	@Test
	void shouldRejectInvalidXml() throws Exception {
		mockMvc.perform(post("/orders") //
						.contentType(MediaType.APPLICATION_XML) //
						.accept(MediaType.APPLICATION_XML) //
						.content(INVALID_XML_CRUD)) //
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldRejectOrderWithMissingLocation() throws Exception {
		mockMvc.perform(post("/orders") //
						.contentType(MediaType.APPLICATION_XML) //
						.accept(MediaType.APPLICATION_XML) //
						.content(MISSING_LOCATION_XML_CRUD)) //
				.andExpect(status().isBadRequest());
	}
}
