package com.salih.restbucks.server.testsuite;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.salih.restbucks.server.service.ProductCatalogTest;
import com.salih.restbucks.server.web.tunneling.OrderControllerTest;
import com.salih.restbucks.server.web.tunneling.util.OrderUriParserTest;

@Suite
@IncludeEngines("junit-jupiter")
@SelectClasses({
		ProductCatalogTest.class,
		OrderUriParserTest.class,
		OrderControllerTest.class
})
public class UnitTests {
}
