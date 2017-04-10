package com.github.eulerlcs.jmr.challenge.xmlparser.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/* jaxb: Java Architecture for XML Binding */
public class JaxbParser {
	@SuppressWarnings("unchecked")
	public static <E> E loadAppConfig(File xml, Class<E> clazz) {
		E entity = null;
		try {
			JAXBContext jc = JAXBContext.newInstance(clazz);
			Unmarshaller u = jc.createUnmarshaller();
			entity = (E) u.unmarshal(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
}
