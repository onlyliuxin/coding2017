package com.test;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.SAXReader;
/**
 * 读取XML
 * @author wang
 *
 */
public class TestDom4j2 {
	public static void main(String[] args) throws Exception {

		SAXReader saxReader = new SAXReader();

		Document document = saxReader.read(new File("src/com/test/students.xml"));

		// 获取根元素
		Element root = document.getRootElement();
		System.out.println("Root: " + root.getName());
		// 获取特定名称的子元素
		List<Element> childList2 = root.elements("hello");
		System.out.println("hello child: " + childList2.size());

		// 获取名字为指定名称的第一个子元素
		Element firstWorldElement = root.element("world");
		// 输出其属性
		System.out.println("first World Attr: "
				+ firstWorldElement.attribute(0).getName() + "="
				+ firstWorldElement.attributeValue("name"));
//		System.out.println("second World Attr: "
//				+ firstWorldElement.attribute(2).getName() + "="
//				+ firstWorldElement.attributeValue("name"));
		
		for (Iterator iter = root.elementIterator(); iter.hasNext();) {
			Element e = (Element) iter.next();
			System.out.println(e.attributeValue("name"));
		}
		
		System.out.println("用DOMReader---------------------------");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		//完整类名 区分
		org.w3c.dom.Document docuemnt2 = db.parse(new File("src/com/test/students.xml"));
		
		DOMReader domReader = new DOMReader();
		
		//将JAXP的Document转换为dom4j的Document
		
		Document document3 = domReader.read(docuemnt2);
		
		Element rootElement = document3.getRootElement();
		System.out.println("Root: "+ rootElement.getName());
		

	}
}
