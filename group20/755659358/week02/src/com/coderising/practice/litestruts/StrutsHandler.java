package com.coderising.practice.litestruts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class StrutsHandler extends DefaultHandler {

	private String actionName;
	private String key;
	private String value;

	private String currentActionName;

	private Map<String, String> result = new HashMap<String, String>();

	public StrutsHandler(String actionName) {
		this.actionName = actionName;
	}

	public Map<String, String> getResult() {
		return result;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("action".equals(qName)) {
			currentActionName = attributes.getValue(attributes.getIndex("name"));
			if (currentActionName.equals(actionName)) {
				value = attributes.getValue(attributes.getIndex("class"));
				result.put("className", value);
			}

		}
		if ("result".equals(qName) && actionName.equals(currentActionName)) {
			key = attributes.getValue("name");
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("result".equals(qName) && actionName.equals(currentActionName)) {
			result.put(key, value);
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		if (actionName.equals(currentActionName)) {
			value = new String(ch, start, length);
		}
	};
}
