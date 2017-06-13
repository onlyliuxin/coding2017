package com.github.eulerlcs.jmr.challenge.xmlparser.sax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* sax: simple api for xml */
public class HelloSaxParser extends DefaultHandler {
	protected static Logger log = LoggerFactory.getLogger(HelloSaxParser.class);

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		log.debug("sax startDocument");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		log.debug("sax startElement qName: [{}]", qName);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		log.debug("sax characters: [{}]", new String(ch, start, length));
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		log.debug("sax endElement qName: [{}]", qName);
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		log.debug("sax endDocument");
	}
}