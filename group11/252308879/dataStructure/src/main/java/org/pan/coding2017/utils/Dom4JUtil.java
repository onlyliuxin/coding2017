package org.pan.coding2017.utils;



import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4JUtil {

	public static final String PATH = "src/p1.xml";
	
	
	public static Document getDocuent(String xmlPath) {

		try {
			//创建解析器
			SAXReader saxReader = new SAXReader();
			//得到Documment
			Document document = saxReader.read(xmlPath);
			return document;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public static void xmlWrite(Document document,String xmlPath){
		
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter xmlWriter = new XMLWriter(new FileWriter(xmlPath),format);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (Exception  e) {
			e.printStackTrace();
		}
		
		
	}

}
