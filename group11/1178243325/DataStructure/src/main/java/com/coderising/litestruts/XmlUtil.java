package com.coderising.litestruts;

import java.io.*;
import java.util.*;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
public class XmlUtil {

	public static String parseXML(String filePath, String actionName) {
		try {
			File file = new File(filePath);  
			SAXReader reader = new SAXReader();  
			Document doc = reader.read(file);  
			Element root = doc.getRootElement();
			for (Iterator iter = root.elementIterator("action"); iter.hasNext();) {
				Element element = (Element)iter.next();	
				Attribute nameAttr = element.attribute("name");
				if (nameAttr.getValue().equals(actionName)) {
					Attribute classAttr = element.attribute("class");
					return classAttr.getValue();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("parse error");
		}
		return null;
	}

}
