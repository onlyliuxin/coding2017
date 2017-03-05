package com.coderising.litestruts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Dom4J 解析XML文件
 * @author ren
 * 
 */
public class Dom4jUtil {
	
	/**
	 * 传入文件路径解析xml文件获取根节点
	 * @param path xml文件的绝对路径
	 * @return Element 根节点
	 */
	public static Element parseXml(String path){
		InputStream is = null;
		Map<String, String> map = new HashMap<String,String>();
		try {
			is = new FileInputStream(path);
			//创建SAXReader读取XML
			SAXReader reader = new SAXReader();
			//根据saxReader的read重写方法可知，既可以通过inputStream输入流来读取，也可以通过file对象来读取   
            Document document = reader.read(is);    
            //获取根节点对象 
            Element rootElement = document.getRootElement();
            return rootElement;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if( is!=null ){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}	
	
	/**
	 * 获取子节点的属性值并添加到map中并返回
	 * @param element xml文件根节点对象
	 * @return map 封装的属性值map对象
	 */
	public static Map<String, String> getAttribute(Element element){
		Map<String, String> map = new HashMap<String, String>();
		List<Element> elements = element.elements();
		for (Element ele : elements) {
			String name = ele.attributeValue("name");
			String clas = ele.attributeValue("class");
          	map.put(name, clas);
        }
		return map;
	}
	
	/**
	 * 根据传入的Action名返回结果JSP
	 * @param element 根节点
	 * @param actionName 标签name属性的value
	 * @return map 封装返回结果jsp的map对象
	 */
	public static Map<String, String> getJspMap(Element element,String actionName){
		Map<String, String> map = new HashMap<String, String>();
		List<Element> actions = element.elements();
		for (Element action : actions) {
			if(actionName.equals(action.attributeValue("name"))){
				List<Element> results = action.elements();
				for (Element result : results) {
					String name = result.attributeValue("name");
					String text = result.getText();
					map.put(name, text);
				}
			}
        }
		return map;
	}
	
	public static void main(String[] args) {
		String path = Dom4jUtil.class.getResource("").getPath()+"struts.xml";
		System.out.println(path);
		Element element = parseXml(path);
		Map<String, String> attribute = getAttribute(element);
		System.out.println(getJspMap(element,"login").get("success"));
	}
}
