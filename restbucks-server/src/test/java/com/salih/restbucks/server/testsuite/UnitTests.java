package com.salih.restbucks.server.testsuite;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.salih.restbucks.server.service.ProductCatalogTest;
import com.salih.restbucks.server.web.pox.PoxOrderControllerTest;
import com.salih.restbucks.server.web.pox.mapper.OrderConfirmationMapperTest;
import com.salih.restbucks.server.web.pox.mapper.XmlOrderMapperTest;
import com.salih.restbucks.server.web.tunneling.OrderControllerTest;
import com.salih.restbucks.server.web.tunneling.util.OrderUriParserTest;
import com.salih.restbucks.server.web.validation.XmlAttributeValidatorTest;
import com.salih.restbucks.server.web.validation.XmlItemValidatorTest;
import com.salih.restbucks.server.web.validation.XmlOrderValidatorTest;
import com.salih.restbucks.server.web.validation.XmlProductValidatorTest;
import com.salih.restbucks.server.web.validation.util.ValidatorRunnerTest;
import com.salih.restbucks.server.web.validation.util.XmlValidationUtilsTest;

@Suite
@IncludeEngines("junit-jupiter")
@SelectClasses({ ProductCatalogTest.class, OrderUriParserTest.class, OrderControllerTest.class, OrderConfirmationMapperTest.class, XmlOrderMapperTest.class,
		PoxOrderControllerTest.class, ValidatorRunnerTest.class, XmlValidationUtilsTest.class, XmlAttributeValidatorTest.class, XmlProductValidatorTest.class,
		XmlItemValidatorTest.class, XmlOrderValidatorTest.class })
public class UnitTests {
}
