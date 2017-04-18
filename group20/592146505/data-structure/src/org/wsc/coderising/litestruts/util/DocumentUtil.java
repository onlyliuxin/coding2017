package org.wsc.coderising.litestruts.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * DOM解析工具类
 * 懒汉单例模式
 * @author Administrator
 * @date 2017年2月28日下午9:45:39
 * @version v1.0
 *
 */
public class DocumentUtil {
	private static DocumentUtil documentUtil;

	private DocumentUtil() {
		super();
	}
	
	/**
	 * 获取实例
	 * @return
	 */
	public static DocumentUtil newInstance(){
		if(documentUtil == null)
			synchronized (DocumentUtil.class) {
				if(documentUtil == null)
					documentUtil = new DocumentUtil();
			}
		return documentUtil;
	}
	
	
	/**
	 * 解析XML文件获取DOM树
	 * @param fileUrl
	 * XML文件路径
	 * @return
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public Document getDocument(String fileUrl) throws ParserConfigurationException, SAXException, IOException{
		return getDocument(new File(fileUrl));
	}
	/**
	 * 解析XML文件获取DOM树
	 * @param file
	 * XML文件实例
	 * @return
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public Document getDocument(File xmlFile) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();//解析器工厂
    	DocumentBuilder db =  dbf.newDocumentBuilder();//解析器
    	return db.parse(xmlFile);//DOM树
	}
	
}
