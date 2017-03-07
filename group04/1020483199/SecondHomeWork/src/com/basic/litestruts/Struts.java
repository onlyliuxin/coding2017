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
			0. ��ȡ�����ļ�struts.xml
	 		1. ����actionName�ҵ����Ӧ��class����LoginAction,ͨ������ʵ��������������
			��parameters�е����ݣ����ö����setter������ ����parameters�е������� 
			("name"="test","password"="1234"),     	
			�Ǿ�Ӧ�õ��� setName��setPassword����
			2. ͨ��������ö����exectue ������ ����÷���ֵ������"success"
			3. ͨ�������ҵ����������getter���������� getMessage��,  
			ͨ�����������ã� ��ֵ�������γ�һ��HashMap , ���� {"message":  "��¼�ɹ�"} ,  
			�ŵ�View�����parameters
			4. ����struts.xml�е� <result> ����,�Լ�execute�ķ���ֵ��  ȷ����һ��jsp��  
			�ŵ�View�����jsp�ֶ��С�
	        */
		   	   View v = new View();//����һ��view�������ڶ���ķ���
		       //1.����һ��DocumentBuilderFactory����
		       DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		       //2.����һ��DocumentBuilder����
		       DocumentBuilder db = null;
		       
		       
			try {
				db = dbf.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
		       //3.ͨ��documentBuilder�������xml�ļ�
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
		    	   
		    	   Node item = nodeList.item(i);//��ȡÿһ��action�ڵ�
		    	   NamedNodeMap attrs = item.getAttributes();//��ȡaction�ڵ��µ���������
		    	   
		    	   Node currentAction = item;//��ŵ�ǰresult�ڵ�
		    	   
		    	   if(attrs.getNamedItem("name").getNodeValue().equals(actionName)){
	    	   			  String className = attrs.getNamedItem("class").getNodeValue();//���login��Ӧ����·��
	    	   			  try {
	    	   				  Class<?> fn = Class.forName(className);
							  Object obj = fn.newInstance();//��������Ķ���
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
							   * ����excute��������success
							   */
							  Object returnValue = fn.getMethod("execute", null).invoke(obj, null);
							  System.out.println(returnValue.toString());//��ӡ����ֵ
							  /**
							   * ͨ�������ҵ�����getter����
							   */
							  //�Ȼ����������
							  Field[] fd = fn.getDeclaredFields();
							  
							  Map newMap = new HashMap();
							  for(int m = 0;m<fd.length;m++){
								  String fieldName = fd[m].getName();
								  String methodName = "get"+init(fieldName);
								  Method method = fn.getMethod(methodName, null);
								  System.out.println(method);//��������ÿһ��getter����
								  String invoke = (String)method.invoke(obj, null);
								  newMap.put(fieldName, invoke);
							  }
							  v.setParameters(newMap);
							  System.out.println(v.getParameters());
							  //fn.getMethod("get"+, parameterTypes)
							  /**
							   * ����struts.xml�е� <result> ����,
							   * �Լ�execute�ķ���ֵ��  ȷ����һ��jsp��  
								  �ŵ�View�����jsp�ֶ���
							   */
							  NodeList childNodes = currentAction.getChildNodes();
							  for(int q = 0;q<childNodes.getLength();q++){
								  NamedNodeMap attributes = childNodes.item(q).getAttributes();//ÿ���ӽڵ���������
								  if(attributes!=null){//����result�ڵ������
									 for(int e = 0;e<attributes.getLength();e++){
										 if(attributes.item(e).getNodeValue().equals(returnValue)){
											 v.setJsp(childNodes.item(q).getTextContent());//��view������success��Ӧ����������
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
	    * ������ĸ��д���������getter�����ķ�����
	    * @param name
	    * @return
	    */
	   private static String init(String name){
		   name = name.substring(0,1).toUpperCase()+name.substring(1);
		   return name;
	   }
}
