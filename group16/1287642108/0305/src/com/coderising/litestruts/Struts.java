package com.coderising.litestruts;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Struts {
    public static View runAction(String actionName, Map<String,String> parameters) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        //创建SAXReader读取器，专门用于读取xml  
    	SAXReader saxReader = new SAXReader();  
        Document document = null;
		try {
			 document = saxReader.read(new File("D:/DemoSpace/coding2017/group16/1287642108/0305/src/com/coderising/litestruts/struts.xml"));
			 Element root = document.getRootElement(); 
			 //根据节点名称找节点
			 Node node = root.selectSingleNode("action[@name='"+actionName+"']");
			 String classPath = ((Element) node).attributeValue("class");
			 //根据类名反射实例化
			 Class<?> onwClass = Class.forName(classPath);
			 Object o = onwClass.newInstance();
			 Method setName = onwClass.getMethod("setName",String.class);
	         Method setPassword = onwClass.getMethod("setPassword",String.class);
	         Method execute = onwClass.getMethod("execute");
	         Method getName = onwClass.getMethod("getName");
	         Method getPassword = onwClass.getMethod("getPassword");
	         Method getMessage = onwClass.getMethod("getMessage");
	         setName.invoke(o,parameters.get("name"));
	         setPassword.invoke(o,parameters.get("password"));
	         String result = (String) execute.invoke(o);
	         //组装params参数
	         HashMap<String,String> map = new HashMap<>();
	         String name = (String) getName.invoke(o);
	         String password = (String) getPassword.invoke(o);
	         String message = (String) getMessage.invoke(o);
	         map.put("name", name);
	         map.put("password", password);
	         map.put("message", message);
	         //组装view数据
	         View view = new View();
	         view.setParameters(map);
	         //根据execute的返回值，找对应的jsp页面路径
	         String jspPath = node.valueOf("//result[@name='"+result+"']");
	         view.setJsp(jspPath);
	         return view;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    	return null;
    }   
}
