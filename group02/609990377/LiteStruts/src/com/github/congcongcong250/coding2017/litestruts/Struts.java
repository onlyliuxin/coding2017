package com.github.congcongcong250.coding2017.litestruts;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.*;

public class Struts {

	public static View runAction(String actionName,
			Map<String, String> parameters) {

		/*
		 * 
		 * 0. 读取配置文件struts.xml
		 * 
		 * 1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象）
		 * 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 ("name"="test" ,
		 * "password"="1234") , 那就应该调用 setName和setPassword方法
		 * 
		 * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		 * 
		 * 3. 通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用， 把值和属性形成一个HashMap , 例如
		 * {"message": "登录成功"} , 放到View对象的parameters
		 * 
		 * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp，
		 * 放到View对象的jsp字段中。
		 */
		
		/*
		 * File f = new File("."); System.out.println(f.getAbsolutePath());
		 */
		String filepath = "./src/com/github/congcongcong250/coding2017/litestruts/struts.xml";
		File inputFile = new File(filepath);
		View view = new View();

		try {
			// Use DOM parser
			Element e = getActionByName(actionName, inputFile);
			
			// Get class and entity by reflection
			String clsName = e.getAttribute("class");
			Class<?> clazz = Class.forName(clsName);
			Object obj = clazz.newInstance();
			
			// Set entity attributes from the parameters passed in
			for (Map.Entry<String, String> entry : parameters.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				PropertyDescriptor pd = new PropertyDescriptor(key,clazz);
				Method setMethod = pd.getWriteMethod();
				setMethod.invoke(obj, value);
			}
			
			// Execute Login class, get result message
			Method exec = clazz.getDeclaredMethod("execute");
			Object res = exec.invoke(obj);
			
			// Get result jsp address according to struts config
			NodeList list = e.getElementsByTagName("result");
			for(int i = 0; i <list.getLength(); i++){
				Element tmp = (Element)list.item(i);
				String name = tmp.getAttribute("name");
				if(res.equals(name)){
					view.setJsp(tmp.getTextContent());
				}
			}
			
			// Put attributes to Map and assign to view 
			Map<String,Object> params = new HashMap<String,Object>();
			Field[] fields = clazz.getDeclaredFields();
			for(Field f : fields){
				PropertyDescriptor descriptor = new PropertyDescriptor(f.getName(),clazz);
				Method getMethod = descriptor.getReadMethod();
				Object value = getMethod.invoke(obj);
				params.put(f.getName(), value);

			}
			view.setParameters(params);
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Return view
		return view;
	}

	private static Element getActionByName(String actionName, File inputFile)
			throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("action");
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				if (eElement.getAttribute("name").equalsIgnoreCase(
						actionName.toLowerCase())) {
					return eElement;
				}
			}
		}
		return null;
	}

}
