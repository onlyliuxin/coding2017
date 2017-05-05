package com.easy.codersing.litestruts;


import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TestApp {
	public static void test1() throws Exception {
		SAXReader reader = new SAXReader();
		File file =new File("src/main/java/com/easy/codersing/litestruts/struts.xml");
		//System.out.println(file.exists());
		Document doc=reader.read(file);
		List actions = doc.selectNodes("//*[@name]");
		for(Iterator it=actions.iterator();it.hasNext();){
			Element action = (Element)it.next();
			List list=action.attributes();
			for(Iterator it2=list.iterator();it2.hasNext();){
				Attribute a=(Attribute)it2.next();
				System.out.println(a.getName()+"="+a.getValue());
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
	/*	Map<String, String> map=new HashMap<>();
		map.put("name", "test");
		map.put("password", "1234");
		
		for (String s : map.keySet()) {
			System.out.println(s);
			System.out.println(map.get(s));
		}*/
		
		
		
		SAXReader reader = new SAXReader();
		File file =new File("src/main/java/com/easy/codersing/litestruts/struts.xml");
		Document doc=reader.read(file);
		Element el_root=doc.getRootElement();
	 	Iterator it= el_root.elementIterator();
	 	while(it.hasNext()){
	 		Object obj=it.next();
	 		Element el_action = (Element)obj;
	 		//System.out.println(el_action.attributeValue("name"));
	 		//System.out.println(el_action.attributeValue("class"));
	 		
	 		Iterator it_row=el_action.elementIterator();
	 		if(el_action.attributeValue("name").equals("login")){
	 			while(it_row.hasNext()){
	 				Element el_result=(Element)it_row.next();
	 				System.out.println(el_result.attributeValue("name"));
	 				System.out.println(el_result.getText());
	 				
	 			}
	 		}
	 	}
	 	
		
		
	}
	
	public static void test3(String[] args) throws Exception {
		Class clazz = Class.forName("com.easy.codersing.litestruts.LoginAction");
		Object obj=clazz.newInstance();
		
		Method method1 =clazz.getMethod("setName",String.class);
		method1.invoke(obj, "张三");
		
		Method method2 = clazz.getMethod("getName",null);
		Object result = method2.invoke(obj);
		System.out.println(result);
		
	}
}	
