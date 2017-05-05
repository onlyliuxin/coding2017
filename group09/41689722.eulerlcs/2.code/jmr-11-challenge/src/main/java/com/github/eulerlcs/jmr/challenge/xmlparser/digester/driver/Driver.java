package com.github.eulerlcs.jmr.challenge.xmlparser.digester.driver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.github.eulerlcs.jmr.challenge.xmlparser.digester.core.HelloDigester;
import com.github.eulerlcs.jmr.challenge.xmlparser.digester.entity.Hello;

public class Driver {
	private final static Logger log = LoggerFactory.getLogger(Driver.class);

	public static void main(String[] args) {
		Digester d = HelloDigester.newInstance();

		try {
			File file = new File("data//xmlparser", "hello.xml");
			Hello helloEntity = (Hello) d.parse(file);
			log.debug("hello.value=[{}]", helloEntity.getValue());
		} catch (IOException | SAXException e) {
			e.printStackTrace();
		}
	}
}
