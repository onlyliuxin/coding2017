package com.test;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class TestDom4j {
	public static void main(String[] args) throws IOException {
		
		//第一种方式：创建文档，并创建根元素
		//创建文档：使用一个Helper的类
		Document document = DocumentHelper.createDocument();
		
		Element root = DocumentHelper.createElement("student");
		document.setRootElement(root);
		
		//第二种方式：创建文档并设置文档的根元素节点
		Element root2 = DocumentHelper.createElement("student");
		Document document2 = DocumentHelper.createDocument(root2);
		
		root2.addAttribute("name", "zhangsan");
		
		Element helloElement = root2.addElement("hello");
		Element worldElement = root2.addElement("world");
		
		helloElement.setText("hello text");
		worldElement.setText("world text");
		
		//输出到控制台
		XMLWriter xmlWriter = new XMLWriter();
		xmlWriter.write(document);
		//输出到文件
		OutputFormat format = new OutputFormat("    ",true);
		
		XMLWriter xmlWriter2 = new XMLWriter(new FileOutputStream("C:\\Users\\wang\\Desktop\\student.xml"),format);
		xmlWriter2.write(document2);
		
		//另一种输出方式
		XMLWriter xmlWriter3 = new XMLWriter(new FileWriter("src/com/test/student2.xml"),format);
		xmlWriter3.write(document2);
		xmlWriter3.flush();
		
		
		
		
		
	}
}
