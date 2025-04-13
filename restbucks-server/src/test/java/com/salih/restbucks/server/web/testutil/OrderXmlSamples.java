package com.salih.restbucks.server.web.testutil;

public class OrderXmlSamples {
	public static final String SAMPLE_XML_POX = """
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

	public static final String SAMPLE_XML_CRUD = """
			<order xmlns="http://restbucks.salih.com/schema/crud">
			    <location>IN_SHOP</location>
			    <currency>USD</currency>
			    <items>
			        <item>
			            <product>
			                <name>Latte</name>
			                <type>DRINK</type>
			            </product>
			            <quantity>2</quantity>
			            <attributes>
			            	<attribute>
			            		<name>SIZE</name>
			            		<value>Large</value>
			            	</attribute>
			            </attributes>
			        </item>
			    </items>
			</order>
			""";

	public static final String INVALID_XML_POX = """
        <order xmlns="http://restbucks.salih.com/schema">
            <items>
                <item>
                    <quantity>2</quantity>
                    <product>
                        <name>Latte</name>
                        <type>INVALID_ENUM</type>
                    </product>
                </item>
            </items>
            <location>IN_SHOP</location>
            <currency>USD</currency>
        </order>
        """;

	public static final String INVALID_XML_CRUD = """
        <order xmlns="http://restbucks.salih.com/schema/crud">
            <location>IN_SHOP</location>
            <currency>USD</currency>
            <items>
                <item>
                    <quantity>2</quantity>
                </item>
            </items>
        </order>
        """;

	public static final String MISSING_LOCATION_XML_POX = """
        <order xmlns="http://restbucks.salih.com/schema">
            <items>
                <item>
                    <quantity>2</quantity>
                    <product>
                        <name>Latte</name>
                        <type>DRINK</type>
                    </product>
                </item>
            </items>
            <currency>USD</currency>
        </order>
        """;

	public static final String MISSING_LOCATION_XML_CRUD = """
        <order xmlns="http://restbucks.salih.com/schema/crud">
            <currency>USD</currency>
            <items>
                <item>
                    <quantity>2</quantity>
                    <product>
                        <name>Latte</name>
                        <type>DRINK</type>
                    </product>
                </item>
            </items>
        </order>
        """;

	private OrderXmlSamples() {
	}
}
