package com.salih.restbucks.server.testsuite;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.salih.restbucks.server.service.ProductCatalogTest;
import com.salih.restbucks.server.web.crud.controller.CrudOrderControllerTest;
import com.salih.restbucks.server.web.crud.mapper.CrudOrderMapperTest;
import com.salih.restbucks.server.web.crud.validation.CrudXmlAttributeValidatorTest;
import com.salih.restbucks.server.web.crud.validation.CrudXmlItemValidatorTest;
import com.salih.restbucks.server.web.crud.validation.CrudXmlOrderValidatorTest;
import com.salih.restbucks.server.web.crud.validation.CrudXmlProductValidatorTest;
import com.salih.restbucks.server.web.pox.PoxOrderControllerTest;
import com.salih.restbucks.server.web.pox.mapper.PoxOrderConfirmationMapperTest;
import com.salih.restbucks.server.web.pox.mapper.PoxOrderMapperTest;
import com.salih.restbucks.server.web.pox.validation.PoxXmlAttributeValidatorTest;
import com.salih.restbucks.server.web.pox.validation.PoxXmlItemValidatorTest;
import com.salih.restbucks.server.web.pox.validation.PoxXmlOrderValidatorTest;
import com.salih.restbucks.server.web.pox.validation.PoxXmlProductValidatorTest;
import com.salih.restbucks.server.web.tunneling.OrderControllerTest;
import com.salih.restbucks.server.web.tunneling.util.OrderUriParserTest;
import com.salih.restbucks.server.web.validation.util.ValidatorRunnerTest;
import com.salih.restbucks.server.web.validation.util.XmlValidationUtilsTest;

@Suite
@IncludeEngines("junit-jupiter")
@SelectClasses({ ProductCatalogTest.class, //
		OrderUriParserTest.class, //
		OrderControllerTest.class, //
		PoxOrderConfirmationMapperTest.class, //
		PoxOrderMapperTest.class, //
		PoxOrderControllerTest.class, //
		ValidatorRunnerTest.class, //
		XmlValidationUtilsTest.class, //
		PoxXmlAttributeValidatorTest.class, //
		PoxXmlProductValidatorTest.class, //
		PoxXmlItemValidatorTest.class, //
		PoxXmlOrderValidatorTest.class, //
		CrudXmlAttributeValidatorTest.class, //
		CrudXmlProductValidatorTest.class, //
		CrudXmlItemValidatorTest.class, //
		CrudXmlOrderValidatorTest.class, //
		CrudOrderControllerTest.class, //
		CrudOrderMapperTest.class })
public class UnitTests {
}
