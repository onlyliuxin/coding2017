package com.github.eulerlcs.jmr.challenge.xmlparser.sax;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Driver {
	public static void main(String[] args) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		File xml = new File("data//xmlparser", "hello.xml");
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(xml, new HelloSaxParser());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
