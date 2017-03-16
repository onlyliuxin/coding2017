package com.coderising.litestruts;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Demo {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws DocumentException {
		SAXReader saxReader = new SAXReader();  
        Document doc =  saxReader.read("src/com/coderising/litestruts/struts.xml");  
        Element root = doc.getRootElement();
        List<Element> list = root.elements("action");
        for(Element action_element : list) {  
            //获取元素action的属性  
        	if("logout".equals(action_element.attributeValue("name"))){
        		System.out.print("action name=" + action_element.attributeValue("name") + "   ");  
                System.out.print("class=" + action_element.attributeValue("class"));  
                System.out.println();
                
              //遍历元素action下所有的result元素  
                for(Element rusult_element : (List<Element>)action_element.elements("result")) {  
                    //获取元素result的属性  
                    System.out.print("result name=" + rusult_element.attributeValue("name") + "   ");  
                    System.out.println("value=" + rusult_element.getText() );  
                }  
        	}
        }  
	}

}
