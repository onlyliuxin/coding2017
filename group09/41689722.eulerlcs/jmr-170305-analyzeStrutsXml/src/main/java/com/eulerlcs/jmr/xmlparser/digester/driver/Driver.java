package com.eulerlcs.jmr.xmlparser.digester.driver;

import java.io.IOException;

import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.eulerlcs.jmr.xmlparser.digester.core.HelloDigester;
import com.eulerlcs.jmr.xmlparser.digester.entity.Hello;
import com.eulerlcs.jmr.xmlparser.digester.util.FileUtils;

public class Driver {
	private final static Logger log = LoggerFactory.getLogger(Driver.class);

	public static void main(String[] args) {
		Digester d = HelloDigester.newInstance();

		try {
			Hello helloEntity = (Hello) d.parse(FileUtils.getXmlFile("hello"));
			log.debug("hello.value=[{}]", helloEntity.getValue());
		} catch (IOException | SAXException e) {
			e.printStackTrace();
		}
	}
}
