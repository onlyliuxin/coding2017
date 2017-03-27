package com.coderising.litestruts;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLHandle {
	
	private Document document = null;
	
	public XMLHandle() throws DocumentException {
		SAXReader saxReader = new SAXReader();
		document = saxReader.read(new File("struts.xml"));
	}
	
	private Element getElement(String actionName) {
		Element root = document.getRootElement();
		Element result = null;
        for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
        	Element element =  it.next();
        	if (element.attribute("name").getValue().equals(actionName)) {
        		//className = element.attribute("class").getValue();
        		result = element;
        		break;
        	}
        }
        return result;
	}
	
    public String getClassName(String actionName) {    	
    	String className = null; 
    	Element element = getElement(actionName);
		if (element != null) {
			className = element.attribute("class").getValue();
		}
        return className;
    }
    
    public String getResult(String actionName, String resultName) {
    	String result = null;
    	Element element = getElement(actionName);
    	
		if (element == null) {
			return null;
		}
		for (Iterator<Element> it = element.elementIterator(); it.hasNext();) {
			Element item = it.next();
			if (item.attribute("name").getValue().equals(resultName)) {
				result = item.getText();
				break;
			}
		}
		
    	return result;
    }
}
