package com.struts;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * DOM方式解析xml
 */
public class DomXmlHelper
{

	private Map<String, String> kv = new HashMap<String, String>();

	public DomXmlHelper () throws DocumentException {
		fetchAttributes();
	}

	// 遍历当前节点下的所有节点
	private void listNodes(Element node, String loop, Map<String, String> kv) {

		// System.out.println("当前节点的名称：" + node.getName());
		if (loop.equals("")) {
			loop += node.getName();
		} else {
			kv.put(loop, node.getName());
			loop += "-" + node.getName();
		}

		// 首先获取当前节点的所有属性节点
		List<Attribute> list = node.attributes();
		// 遍历属性节点
		for (Attribute attribute : list) {
			// System.out.println("属性 "+attribute.getName() +":" +
			// attribute.getValue());
			kv.put(loop, attribute.getValue());
			loop += "-" + attribute.getValue();
			// System.out.println("loop: " + loop);
		}

		// 如果当前节点内容不为空，则输出
		if (!(node.getTextTrim().equals(""))) {
			// System.out.println("内容 " + node.getName() + "：" +
			// node.getText());
			kv.put(loop, node.getText());
			loop += "-" + node.getText();
			// System.out.println("loop: " + loop);
		}

		// 同时迭代当前节点下面的所有子节点
		// 使用递归
		Iterator<Element> iterator = node.elementIterator();
		while (iterator.hasNext()) {
			Element e = iterator.next();
			listNodes(e, loop, kv);
		}
	}

	private void fetchAttributes() throws DocumentException {
		// 创建SAXReader对象
		SAXReader reader = new SAXReader();
		// 读取文件 转换成Document
		Document document = (Document) reader.read(new File(
				"./src/com/struts/struts.xml"));

		// 获取根节点元素对象
		Element root = ((org.dom4j.Document) document).getRootElement();

		listNodes(root, "", kv);

		for (Map.Entry<String, String> entity : kv.entrySet()) {
			// System.out.println("key: " + entity.getKey() + " , value: " +
			// entity.getValue());
		}
	}

	public String getActionView(String action, String method) {
		String key = "struts-action-" + action;
		String className = kv.get(key);
		key += "-" + className + "-result-" + method;
		return kv.get(key);
	}

	public String getActionClassByName(String action) {
		String key = "struts-action-" + action;
		return kv.get(key);
	}

	// public static void main(String[] args) throws DocumentException {
	// DomXmlHelper dm = new DomXmlHelper();
	// System.out.println(dm.getActionClassByName("login"));
	// System.out.println(dm.getActionView("login", "success"));
	// System.out.println(dm.getActionView("login", "fail"));
	//
	// System.out.println(dm.getActionClassByName("logout"));
	// System.out.println(dm.getActionView("logout", "success"));
	// System.out.println(dm.getActionView("logout", "error"));
	// }

}
