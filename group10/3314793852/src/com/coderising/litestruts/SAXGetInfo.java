	/*
	 *该类用于读取struts.xml文件中的数据，并把它存储到ActionType类的对象中去。 
	 */
	package com.coderising.litestruts;
	
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.util.ArrayList;
	import java.util.HashMap;

	import javax.xml.parsers.ParserConfigurationException;
	import javax.xml.parsers.SAXParser;
	import javax.xml.parsers.SAXParserFactory;

	import org.xml.sax.InputSource;
	import org.xml.sax.SAXException;
	import org.xml.sax.XMLReader;

	 
	
	public class SAXGetInfo {
		
		public ArrayList<HashMap<String, String>> getDate() throws SAXException, IOException, ParserConfigurationException{
			//创建解析工厂
			SAXParserFactory factory=SAXParserFactory.newInstance();
			
			//创建解析器
			SAXParser parser=factory.newSAXParser();
			
			//文件地址
			String fileName="E:/CODING2017/Code/coding2017/group10/3314793852/second/src/com/coderising/litestruts/struts.xml";
			//设置内容处理器
			DealWithInfo handler=new DealWithInfo();
			parser.parse(fileName, handler);
			
			ArrayList<HashMap<String, String>> list=handler.getDate();
			return list;	//将保存在list集合中的配置信息返回。
		}
				
	}
