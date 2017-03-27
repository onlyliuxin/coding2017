package com.coderising.litestruts.util;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlUtil {
	/**
	 * 根据Xml文件生成Document对象
	 * 
	 * @param file
	 *            xml文件路径
	 * @return Document对象
	 * @throws DocumentException
	 */
	public static Document getDocument(File file) throws DocumentException {
		SAXReader xmlReader = new SAXReader();
		return xmlReader.read(file);
	}

	/**
	 * 根据输入流生成Document对象
	 * 
	 * @param is
	 *            输入流
	 * @return Document对象
	 * @throws DocumentException
	 */
	public static Document getDocument(InputStream is) throws DocumentException {
		SAXReader xmlReader = new SAXReader();
		return xmlReader.read(is);
	}

	/**
	 * 根据Document得到根结点
	 * 
	 * @param doc
	 *            Document目录
	 * @return 根结点
	 */
	public static Element getRoot(Document doc) {
		return doc.getRootElement();
	}

	/**
	 * 取出当前结点下的所有子结点
	 * 
	 * @param root
	 *            当前结点
	 * @return 一组Element
	 */
	public static List<Element> getElements(Element root) {
		return root.elements();
	}

	/**
	 * 根据元素名称返回一组Element
	 * 
	 * @param root
	 *            当前结点
	 * @param name
	 *            要返回的元素名称
	 * @return 一组Element
	 */
	public static List<Element> getElementsByName(Element root, String name) {
		return root.elements(name);
	}

	/**
	 * 根据元素名称返回一个元素(如果有多个元素的话，只返回第一个)
	 * 
	 * @param root
	 *            当前结点
	 * @param name
	 *            要返回的元素名称
	 * @return 一个Element元素
	 */
	public static Element getElementByName(Element root, String name) {
		return root.element(name);
	}

	/**
	 * 根据当前元素,返回该元素的所有属性
	 * 
	 * @param root
	 *            当前结点
	 * @return 当前结点的所有属性
	 */
	public static List<Attribute> getAttributes(Element root) {
		return root.attributes();
	}

	/**
	 * 根据属性名称,返回当前元素的某个属性
	 * 
	 * @param root
	 *            当前结点
	 * @return 当前结点的一个属性
	 */
	public static Attribute getAttributeByName(Element root, String name) {
		return root.attribute(name);
	}
}
