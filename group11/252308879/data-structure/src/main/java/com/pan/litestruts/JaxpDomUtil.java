package com.pan.litestruts;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JaxpDomUtil {
	public static final String XMLPATH = JaxpDomUtil.class.getClassLoader().getResource("struts.xml").getPath();

	/**
	 *  通过 解析器 获取到 Document
	 * @return
	 */
	public static Document getDocument() {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(XMLPATH);
			return document;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	/**
	 *  回写 XML 方法
	 * @param document
	 */
	public static void tranFormMethod(Document document) {
		try {
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(new DOMSource(document), new StreamResult(
					XMLPATH));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 递归调用  获取所有的元素 并打印元素名称
	 * @param node
	 */
	private static void listElement(Node node) {
		// 判断是元素类型时才打印
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			System.out.println(node.getNodeName());
		}

		// 获得一层子节点
		NodeList nodelist = node.getChildNodes();
		for (int i = 0; i < nodelist.getLength(); i++) {
			// 得到每一个子节点
			Node nodeChild = nodelist.item(i);

			// 递归调用
			listElement(nodeChild);
		}

	}

	/**
	 * 获取所有的 元素名称
	 */
	public static void getListElement() {

		Document document = JaxpDomUtil.getDocument();
		listElement(document);
	}

	/**
	 * 删除<sex>nan</sex>节点
	 */
	public static void delSex() {

		Document document = JaxpDomUtil.getDocument();

		// 获取<sex>元素
		Node nodeSex = document.getElementsByTagName("sex").item(0);

		// 得到父节点
		Node parent = nodeSex.getParentNode();

		// 通过父节点删除
		parent.removeChild(nodeSex);

		// 回写XML
		JaxpDomUtil.tranFormMethod(document);

	}
	
	/**
	 * 修改 sex 标签的 内容为nv
	 */
	public  static void modifySex() {
		Document document = JaxpDomUtil.getDocument();
		Node nodeSex = document.getElementsByTagName("sex").item(0);
		nodeSex.setTextContent("nv");
		JaxpDomUtil.tranFormMethod(document);
		
	}
	
	/**
	 *  为第一个p1 增加 <sex>nv</sex>
	 */
	public static void addSex(){
		Document document = JaxpDomUtil.getDocument();
		Node p1Node = document.getElementsByTagName("p1").item(0);
		
		//通过 Document 创建 Element
		Element sexElement = document.createElement("sex");
		sexElement.setTextContent("nv");
		p1Node.appendChild(sexElement);
		JaxpDomUtil.tranFormMethod(document);
		
	}
	
	/**
	 * 查询xml中第一个name元素的值
	 */
	public static void selectSin(){
		Document document = JaxpDomUtil.getDocument();
		Node nameNode = document.getElementsByTagName("name").item(0);
		String name = nameNode.getTextContent();
		System.out.println(name);
	}
	
	/**
	 *  查询所有name元素的值
	 */
	public  static void selectAll(){
		Document document = JaxpDomUtil.getDocument();
		NodeList nodeList = document.getElementsByTagName("name");
		for (int i = 0; i < nodeList.getLength(); i++) {
			System.out.println(nodeList.item(i).getTextContent());
		}
	}

}
