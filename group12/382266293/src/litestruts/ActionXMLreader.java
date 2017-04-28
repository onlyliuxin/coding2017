package litestruts;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class ActionXMLreader {

	public Node getRootNode(String add) {
		SAXReader reader = new SAXReader();
		Document document = null;
		Node root = null;
		try {
			document = reader.read(add);
			root = document.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return root;
	}

	public String parseClass(Node root, String attr) {

		@SuppressWarnings("rawtypes")
		List list = root.selectNodes("action[@name='" + attr + "']");
		String clazz = null;
		for (Object o : list) {
			Element e = (Element) o;
			clazz = e.attributeValue("class");
		}
		return clazz;
	}

	public String parseResult(Node root, String attr, String result) {

		@SuppressWarnings("rawtypes")
		List list = root.selectNodes("action[@name='" + attr + "']/result[@name='" + result + "']");

		String jsp = null;
		for (Object o : list) {
			Element e = (Element) o;
			jsp = e.getTextTrim();
		}
		return jsp;
	}

}
