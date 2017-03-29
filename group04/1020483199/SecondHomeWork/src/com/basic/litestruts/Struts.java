package com.basic.litestruts;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Struts {
	  
	   public static View runAction(String actionName, Map<String,String> parameters){
	        /*
			0. 读取配置文件struts.xml
	 		1. 根据actionName找到相对应的class例如LoginAction,通过反射实例化（创建对象）
			据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
			("name"="test","password"="1234"),     	
			那就应该调用 setName和setPassword方法
			2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
			3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
			通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
			放到View对象的parameters
			4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
			放到View对象的jsp字段中。
	        */
		   	   View v = new View();//定义一个view对象，用于对象的返回
		       //1.创建一个DocumentBuilderFactory对象
		       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		       //2.创建一个DocumentBuilder对象
		       DocumentBuilder db = null;
		       
		       
			try {
				db = dbf.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		       //3.通过documentBuilder对象加载xml文件
		       Document document = null;
			try {
				document = db.parse("src/com/basic/litestruts/struts.xml");
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		       
		       NodeList nodeList = document.getElementsByTagName("action");
		       //System.out.println(nodeList.getLength());
		       
		       for(int i = 0;i<nodeList.getLength();i++){
		    	   
		    	   Node item = nodeList.item(i);//获取每一个action节点
		    	   NamedNodeMap attrs = item.getAttributes();//获取action节点下的所有属性
		    	   
		    	   Node currentAction = item;//存放当前result节点
		    	   
		    	   if(attrs.getNamedItem("name").getNodeValue().equals(actionName)){
	    	   			  String className = attrs.getNamedItem("class").getNodeValue();//获得login对应的类路径
	    	   			  try {
	    	   				  Class<?> fn = Class.forName(className);
							  Object obj = fn.newInstance();//反射出来的对象
							  Method methodSetName = fn.getMethod("setName", String.class);
							  Method methodSetPwd = fn.getMethod("setPassword", String.class);
							  for(Map.Entry<String, String> m :parameters.entrySet()){
								  if(m.getKey().equals("name")){
									methodSetName.invoke(obj, m.getValue());
								  }
								  if(m.getKey().equals("password")){
									  methodSetPwd.invoke(obj, m.getValue());
								  }
							  }
							  /**
							   * 调用excute方法返回success
							   */
							  Object returnValue = fn.getMethod("execute", null).invoke(obj, null);
							  System.out.println(returnValue.toString());//打印返回值
							  /**
							   * 通过反射找到所有getter方法
							   */
							  //先获得所有属性
							  Field[] fd = fn.getDeclaredFields();
							  
							  Map newMap = new HashMap();
							  for(int m = 0;m<fd.length;m++){
								  String fieldName = fd[m].getName();
								  String methodName = "get"+init(fieldName);
								  Method method = fn.getMethod(methodName, null);
								  System.out.println(method);//遍历出的每一个getter方法
								  String invoke = (String)method.invoke(obj, null);
								  newMap.put(fieldName, invoke);
							  }
							  v.setParameters(newMap);
							  System.out.println(v.getParameters());
							  //fn.getMethod("get"+, parameterTypes)
							  /**
							   * 根据struts.xml中的 <result> 配置,
							   * 以及execute的返回值，  确定哪一个jsp，  
								  放到View对象的jsp字段中
							   */
							  NodeList childNodes = currentAction.getChildNodes();
							  for(int q = 0;q<childNodes.getLength();q++){
								  NamedNodeMap attributes = childNodes.item(q).getAttributes();//每个子节点所有属性
								  if(attributes!=null){//两个result节点的属性
									 for(int e = 0;e<attributes.getLength();e++){
										 if(attributes.item(e).getNodeValue().equals(returnValue)){
											 v.setJsp(childNodes.item(q).getTextContent());//在view中塞入success对应的文章内容
										 }
									 }
								  }
							  }
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
	    	   		  }
		    	   }
		       
		    
	    	return v;
	    }    
	   /**
	    * 将首字母大写，用于组成getter方法的方法名
	    * @param name
	    * @return
	    */
	   private static String init(String name){
		   name = name.substring(0,1).toUpperCase()+name.substring(1);
		   return name;
	   }
}
