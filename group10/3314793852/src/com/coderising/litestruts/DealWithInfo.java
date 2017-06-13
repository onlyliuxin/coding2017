	/*
	 * 该类用于读取处理XML文件。
	 */
	package com.coderising.litestruts;

	import java.awt.List;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.Map;
	
	import org.xml.sax.Attributes;
	import org.xml.sax.SAXException;
	import org.xml.sax.helpers.DefaultHandler;




	
	
	public class DealWithInfo extends DefaultHandler {
		
		private String key;
		private String value;
		private HashMap<String, String> aMap=new HashMap<String,String>();	//一个用来存储。

		private ArrayList<HashMap<String, String>> list=new ArrayList<HashMap<String,String>>();;						//List集合用来保存Map集合。
		
		private String currentTag;
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {
			currentTag =qName;
			
			if("action".equals(currentTag)){
				key=attributes.getValue(0);		//action的name属性。
				value=attributes.getValue(1);	//action的class属性。
				
				//在将属性成对的保存到一个Map集合中。
				aMap.put(key, value);
				
				//保存后将中间变量变为null.
				key=null;
				value=null;
			}
			
			if("result".equals(currentTag)){
				key=attributes.getValue(0);		//result的name属性。
			}
			
		}

		
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			if("result".equals(currentTag)){
				String name=new String(ch,start,length);
				value=name;
				//将属性成对的保存到一个Map集合中。
				
				aMap.put(key, value);
				
				//保存后将中间变量清空。
				key=null;
				value=null;
				currentTag=null;
			}
			
		}
		
		
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if("action".equals(qName)){
				
				list.add(aMap);
				
				
			aMap=new HashMap<String,String>();
				
				
			}
			
		}
		
		//返回list集合。
		public ArrayList<HashMap<String, String>> getDate(){
					return list;
		}
	}
