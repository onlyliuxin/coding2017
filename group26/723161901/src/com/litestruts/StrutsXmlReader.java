package com.litestruts;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.litestruts.strutsBean.Action;

public class StrutsXmlReader {
	private File file;
	private HashMap actMap = new HashMap();

	public StrutsXmlReader(File file) {
		super();
		this.file = file;
	}


	public Map loadXml() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(file);
		NodeList nl = doc.getElementsByTagName("action");
		for (int i = 0; i < nl.getLength(); i++) {
			Element book = (Element) nl.item(i);
			String name = book.getAttribute("name");
			String claz = book.getAttribute("class");
			Action act = new Action(name, claz);
			System.out.println(name);
			System.out.println(claz);
			NodeList bookNode = book.getChildNodes();
			HashMap<String, String> paraMap = new HashMap();
			for (int j = 0; j < bookNode.getLength(); j++) {
				Node bookCh = bookNode.item(j);
				if (bookCh.getNodeType() == Node.ELEMENT_NODE) {
					String valTag = bookCh.getTextContent();
					NamedNodeMap attrs = bookCh.getAttributes();
					String resultsValue = attrs.getNamedItem("name").getNodeValue();
					paraMap.put(resultsValue, valTag);
				}
				act.setParameters(paraMap);
			}
			actMap.put(act.getName(), act);
		}
		return actMap;
	}
}
