package com.coderising.util;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;


public class ReadXmlUtil {
	private static Logger log=Logger.getLogger(ReadXmlUtil.class);
	// para 文件名
	// 从文件读取想xml，输入文件名，返回document
	public static Document getDocumentByFileName(String FileName){
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(FileName);
			return doc;
		} catch (DocumentException e) {
			e.printStackTrace();
			log.info("警告：出错了，由于"+e.getMessage());
		}
		return null;
		
	}
	public static Document getDocumentByXML(String xml){
		return getDocumentByXMLAndPara(xml, "utf-8");
	}
	public static Document getDocumentByXMLAndPara(String xml,String chatSet){
		SAXReader reader = new SAXReader();
		reader.setEncoding(chatSet);
		InputStream in = new ByteArrayInputStream(xml.getBytes());
		try {
			Document doc = reader.read(in);
			return doc;
		} catch (DocumentException e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}
		return null;
	}

}
