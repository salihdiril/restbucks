package com.salih.restbucks.common.log.helpers;

import java.io.StringWriter;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

public class XmlStringSerializer {

	private XmlStringSerializer() {
	}

	public static String toXmlString(Object jaxbObject) {
		try {
			JAXBContext context = JAXBContext.newInstance(jaxbObject.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			marshaller.marshal(jaxbObject, writer);
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException("Failed to convert object to XML string", e);
		}
	}
}
