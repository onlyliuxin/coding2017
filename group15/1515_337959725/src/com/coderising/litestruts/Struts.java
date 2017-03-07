package com.coderising.litestruts;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class Struts {

    @SuppressWarnings("unchecked")
	public static View runAction(String actionName, Map<String,String> parameters) {

        /*
         
		0. ��ȡ�����ļ�struts.xml
 		
 		1. ����actionName�ҵ����Ӧ��class �� ����LoginAction,   ͨ������ʵ��������������
		��parameters�е����ݣ����ö����setter������ ����parameters�е������� 
		("name"="test" ,  "password"="1234") ,     	
		�Ǿ�Ӧ�õ��� setName��setPassword����
		
		2. ͨ��������ö����exectue ������ ����÷���ֵ������"success"
		
		3. ͨ�������ҵ����������getter���������� getMessage��,  
		ͨ�����������ã� ��ֵ�������γ�һ��HashMap , ���� {"message":  "��¼�ɹ�"} ,  
		�ŵ�View�����parameters
		
		4. ����struts.xml�е� <result> ����,�Լ�execute�ķ���ֵ��  ȷ����һ��jsp��  
		�ŵ�View�����jsp�ֶ��С�
        
        */
    	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    	View view = null;
    	try {
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
			File f = new File("E:/gitProject/coding2017/group15/1515_337959725/src/com/coderising/litestruts/struts.xml");
			Document document = documentBuilder.parse(f);
			NodeList actionList = document.getElementsByTagName("action");
			Node node = null;
			String className="";
			for(int i=0;i<actionList.getLength();i++){
				Node action = actionList.item(i);
				NamedNodeMap attrs = action.getAttributes();
				for(int j=0;j<attrs.getLength();j++){
					String nodeValue = attrs.item(j).getNodeValue();
					if(actionName.equals(nodeValue)){
						node=action;
					}
				}
			}
			NamedNodeMap nodeAtts = node.getAttributes();
			for(int j=0;j<nodeAtts.getLength();j++){
				String nodeName =nodeAtts.item(j).getNodeName();
				if("class".equals(nodeName)){
					className=nodeAtts.item(j).getNodeValue();
				}
			}
			Class clazz = Class.forName(className);
			Object obj = clazz.newInstance();
			String methodName="";
			for (String key : parameters.keySet()) {
				methodName="set"+key.substring(0, 1).toUpperCase()+key.substring(1,key.length());
				Method method= clazz.getMethod(methodName, String.class);
				method.invoke(obj, parameters.get(key));
			}
			Method method1 = clazz.getMethod("execute");
			String rt = (String)method1.invoke(obj);
			Method method2 = clazz.getMethod("getMessage");
			String message = (String)method2.invoke(obj);
			NodeList childNodes = node.getChildNodes();
			Node result = null;
			for(int j=0;j<childNodes.getLength();j++){
				Node child= childNodes.item(j);
				if(child.getNodeType()==Node.ELEMENT_NODE){NamedNodeMap attrs = child.getAttributes();
				for(int k=0;k<attrs.getLength();k++){
					String nodeValue = attrs.item(k).getNodeValue();
					if(rt.equals(nodeValue)){
						result=child;
					}
				}}
			}
			String jsp = result.getNodeValue();
			parameters.put("Message", message);
			view=new View();
			view.setJsp(jsp);
			view.setParameters(parameters);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;
    }    

}
