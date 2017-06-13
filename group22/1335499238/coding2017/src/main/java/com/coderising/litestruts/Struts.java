package com.coderising.litestruts;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {

	
    public static View runAction(String actionName, Map<String,String> parameters) {

        /*
         
		0. 读取配置文件struts.xml
 		
 		1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
		据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
		("name"="test" ,  "password"="1234") ,     	
		那就应该调用 setName和setPassword方法
		
		2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		
		3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
		放到View对象的parameters
		
		4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
		放到View对象的jsp字段中。
        
        */
    	View view = null;
    	SAXReader reader = new SAXReader();
    	InputStream resourceAsStream = Struts.class.getResourceAsStream("struts.xml");
    	try {
			Document document = reader.read(resourceAsStream);
			Element rootElement = document.getRootElement();
			
			Element element = findElement(rootElement, actionName);
			if(element == null){
				throw new RuntimeException("指定节点不存在");
			}
			//子节点数据信息
			Map<String, String> elementData = getElementData(element);
			Class<?> forName = Class.forName(element.attribute("class").getValue());
			Object classInstance = forName.newInstance();
			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(forName).getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				String name = propertyDescriptor.getName();
				Method readMethod = propertyDescriptor.getWriteMethod();
				if(parameters != null && parameters.size() > 0 && parameters.containsKey(name) && readMethod != null){
					readMethod.setAccessible(true);
					readMethod.invoke(classInstance, parameters.get(name));
				}
			}
			Method method = forName.getDeclaredMethod("execute");
			method.setAccessible(true);
			Object o = method.invoke(classInstance);
			Map<String,Object> viewMap = new HashMap<String, Object>();
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				String name = propertyDescriptor.getName();
				Method readMethod = propertyDescriptor.getReadMethod();
				if(readMethod != null && !"class".equals(name)){
					viewMap.put(name, readMethod.invoke(classInstance));
				}
			}
			view = new View();
			view.setParameters(viewMap);
			if(elementData != null && elementData.size() > 0 && elementData.containsKey(o)){
				view.setJsp(elementData.get(o));
			}else{
				view.setJsp(null);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return view;
    }

    /**
     * 根据名称获取指定节点
     * @param rootElement	根节点
     * @param actionName	action名称
     * @return
     */
    private static Element findElement(Element rootElement,String actionName){
    	@SuppressWarnings("unchecked")
		List<Element> elements = rootElement.elements("action");
		for (Element element : elements) {
			Attribute name = element.attribute("name");
			if(name == null){
				return null;
			}
			String value = name.getValue();
			if(value == null || !value.equals(actionName)){
				continue;
			}
			Attribute actionClass = element.attribute("class");
			if(actionClass == null || actionClass.getValue() == null){
				return null;
			}
			return element;
		}
    	return null;
    }
    
    /**
     * 获取action下的result节点信息
     * @param element	action节点
     * @return
     */
    private static Map<String, String> getElementData(Element element){
    	Map<String, String> map = null;
    	@SuppressWarnings("unchecked")
		List<Element> elements = element.elements("result");
    	for (Element elementSub : elements) {
			if(map == null){
				map = new HashMap<String, String>();
			}
			Attribute attribute = elementSub.attribute("name");
			if(attribute!=null){
				map.put(attribute.getValue(), elementSub.getTextTrim());
			}
		}
    	return map;
    }
}
