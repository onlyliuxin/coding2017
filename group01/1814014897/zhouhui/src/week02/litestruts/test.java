package week02.litestruts;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import week01.BasicDataStructure.LinkedList;
import week01.BasicDataStructure.List;

public class test {
	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder;
    	Document doc = null; 
		try {
			builder = factory.newDocumentBuilder();
			File f = new File("src/week02/litestruts/struts.xml");
	    	doc = builder.parse(f);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	////根据actionName找到相对应的class
		Element root = doc.getDocumentElement();
    	NodeList actionNode = root.getElementsByTagName("action");
    	Element action = null;
    	for(int i=0;i<actionNode.getLength();i++){
    		NamedNodeMap attributes = actionNode.item(i).getAttributes();
    		for(int j=0;j<attributes.getLength();j++){
    			Node attribute = attributes.item(j);
    			String value = attribute.getNodeValue();
    			if(value.equals("login")){
    				action = (Element) actionNode.item(i);
    			}
    		}
    	}
    	
    	String actionClass = action.getAttribute("class");
    	try {
			Class<?> cls = Class.forName(actionClass);
			Object obj = cls.newInstance();
			Method setName = cls.getMethod("setName", String.class); 
			Method setPassword = cls.getMethod("setPassword", String.class);
			setName.invoke(obj, "test");
			setPassword.invoke(obj, "1234");
			
			Method execute = cls.getMethod("execute");
			String exe = (String) execute.invoke(obj);
			System.out.println(exe);
			
			Method[] met = cls.getDeclaredMethods();
			List list = new LinkedList();
			for(int i=0;i<met.length;i++){
				if(met[i].getName().startsWith("get")){
					list.add(met[i]);
				}
			}
			
			Method[] getter = new Method[list.size()];
			for(int i=0;i<list.size();i++){
				getter[i] = (Method) list.get(i);
			}
			for(int i=0;i<getter.length;i++)
			System.out.println(getter[i]);
			
			Map<String ,String> param = new HashMap<>();
			for(int i=0;i<getter.length;i++){
				if(getter[i].equals(cls.getMethod("getMessage")))
				param.put("message",(String) getter[i].invoke(obj));
			}
			//parameters = param;
			System.out.println(param.get("message"));
			System.out.println(action.getFirstChild().getNodeValue());
			
			NodeList resultNode = action.getElementsByTagName("result");
			for(int i=0;i<resultNode.getLength();i++){
				Element resultElement = (Element) resultNode.item(i);
				System.out.println(resultElement.getAttribute("name"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
