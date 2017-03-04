package assignment2_26;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlPhraser {
	private File file;
	List<Element> actionElements;

	public XmlPhraser(File file) {
		this.file = file;
		actionElements = new ArrayList<>();
		getActionsFromFile();
	}

	public String getClassNameByActionName(String actionName) {
		Element action = getElementByActionName(actionName);
		return action.getAttribute("class");
	}

	public String getResultJsp(String actionName, String resultName) {
		Element action = getElementByActionName(actionName);
		NodeList results = action.getChildNodes();
		for (int i = 0; i < results.getLength(); i++) {
			Node child = results.item(i);
			if (child instanceof Element) {
				Element result = (Element) child;
				if (result.getAttribute("name").equals(resultName))
					return result.getTextContent();
			}
		}
		throw new RuntimeException("not found result named:" + resultName);
	}

	private Element getElementByActionName(String actionName) {
		for (Element element : actionElements) {
			if (element.getAttribute("name").equals(actionName)) {
				return element;
			}
		}
		throw new RuntimeException("no such element named " + actionName);
	}

	private void getActionsFromFile() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(file);
			Element root = document.getDocumentElement();
			NodeList children = root.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				Node child = children.item(i);
				if (child instanceof Element)
					actionElements.add((Element) child);
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}
