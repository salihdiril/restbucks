package com.salih.restbucks.server.testsuite;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.salih.restbucks.server.integration.OrderIntegrationTest;

@Suite
@IncludeEngines("junit-jupiter")
@SelectClasses({ OrderIntegrationTest.class })
public class IntegrationTests {
}
