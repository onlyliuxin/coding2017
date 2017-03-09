package com.coderising.litestruts;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ReadXml {

	private Document document = null;
	private HashMap<String, String> hashMap;

	public ReadXml(String filename) {
		try {
			document = new SAXReader().read((filename));
			hashMap = new HashMap<String, String>();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String parseXml(String actionName) {

		// List<?> actions = document.selectNodes("//struts/action");
		String className = null;
		Element root = document.getRootElement();
		List<?> actions = root.elements("action");
		if (actions.isEmpty()) {
			return null;
		}
		for (Iterator<?> iter = actions.iterator(); iter.hasNext();) {
			Element element = (Element) iter.next();
			Attribute attr1 = element.attribute("name");
			if (attr1.getValue().equals(actionName)) {
				Attribute attr2 = element.attribute("class");
				className = attr2.getValue();
				//获取子元素的的属性值
				for (Iterator<?> iterator = element.elementIterator(); iterator
						.hasNext();) {
					Element childElement = (Element) iterator.next();
					Attribute childAttribute = childElement.attribute("name");
					hashMap.put(childAttribute.getValue(),
							childElement.getText());
				}
			}

		}
		return className;
	}

	public String getJsp(String result) {
		if (result == null) {
			return null;
		}
		String string_jsp = null;
		if (!hashMap.isEmpty()) {
			for (String string : hashMap.keySet()) {
				if (result.equals(string)) {
					string_jsp = hashMap.get(string);
				}
			}
		}
		return string_jsp;
	}
}
