package org.pan.coding2017.utils;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class JaxpSAXPUtil {
	public static void main(String[] args) {
		/*
		 *	1、创建解析器工厂
		 *	2、创建解析器
		 *	3、执行 parse 方法
		 *
		 * 	4、自己创建一个类、继承DefaultHandler
		 * 	5、重写类里面的三个方法
		 */
		
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			saxParser.parse("src/p1.xml",new MyDeafultHandler2() );
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}

class MyDeafultHandler1 extends DefaultHandler{

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.println("<"+qName+">");
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		System.out.println(new String(ch,start,length));
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.println("<"+qName+"/>");
	}
	
}

//实现获取所有的name元素的值
class MyDeafultHandler2 extends DefaultHandler{

	boolean flag = false;
	int index = 1;
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		//判断qName 是否为 name 元素
		if("name".equals(qName) && index == 2){
			flag = true;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(flag ==  true){
			System.out.println(new String(ch, start, length));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if("name".equals(qName)){
			flag = false;
			index++ ;
		}
	}

	
}
