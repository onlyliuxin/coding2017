package com.coderising.parser;

import com.coderising.parser.XmlParser.eNode;

public class TestDriver {

	public static void main(String[] args) {
		XmlParser testParser = new XmlParser("src/xml/input.xml");
		String input = "<action name = \"login\" class=\"com.codersing.action.LoginAction\">";
		System.out.println();
		//eNode e = testParser.parseRawData(input);
		//XmlParser.dumpNodePreOrder(e);
//		try{
//			testParser.loadFile(testParser.getFile());
//		}catch(Exception e){
//			e.printStackTrace();
//		}
	}
	
	

}
