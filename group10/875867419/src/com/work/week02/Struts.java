package com.work.week02;


import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters){
    	View view = new View();
    	try{
    		StrutsXmlDao dao = loadXmlByDom4j(actionName);
    		view = reflectCreateObj(dao, parameters);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return view;
    }    
    
    /**
     * 读取xml文件
     * @throws DocumentException 
     */
    @SuppressWarnings("unchecked")
	private static StrutsXmlDao loadXmlByDom4j(String actionName) throws DocumentException{
    	StrutsXmlDao dao = new StrutsXmlDao();
    	SAXReader reader = new SAXReader();			//创建SAXReader对象
    	Document doc = reader.read(new File("src/com/work/week02/struts.xml"));		//创建Document对象
    	Element root = doc.getRootElement();				//获取根节点
    	
    	List<Element> list = root.elements();
    	for (Element element : list) {
    		if(element.attributeValue("name").equals(actionName)){
    			dao.setActionName(element.attributeValue("name"));
    			dao.setActionClass(element.attributeValue("class"));
    			List<Element> branchs = element.elements();
    			List<Map<String, Object>> actionResult = new ArrayList<Map<String, Object>>();
    			for (Element branch : branchs) {
    				Map<String, Object> map = new HashMap<String, Object>();
    				map.put(branch.attributeValue("name"), branch.getTextTrim());
    				actionResult.add(map);
				}
    			dao.setActionResult(actionResult);
    		}
			
		}
    	
    	return dao;
    }
    
    /**
     * 通过反射创建指定对象
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     */
    private static View reflectCreateObj(StrutsXmlDao dao, Map<String,String> parameters) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		View view = new View();
		Class<?> clazz = Class.forName(dao.getActionClass());
    	//实例化对象
    	Object obj = clazz.newInstance();
    	//调用set方法设置参数
    	Set<String> keys = parameters.keySet();
    	for (String key : keys) {
			String value = parameters.get(key);
			Method setMethod = clazz.getDeclaredMethod("set"+key.substring(0, 1).toUpperCase()+key.substring(1), new Class[]{String.class});
			setMethod.invoke(obj, value);
		}
    	
    	//调用execute()方法执行
    	Method execute = clazz.getMethod("execute");
    	Object reuslt = execute.invoke(obj);			//execute()方法返回值
    	List<Map<String, Object>> actionResult = dao.getActionResult();
    	//获取返回值对应的跳转jsp
    	for (Map<String, Object> map : actionResult) {
			if(map.containsKey(reuslt)){
				view.setJsp(map.get(reuslt).toString());
			}
		}
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	//调用get方法将参数存入view中
    	Field[] fields = clazz.getDeclaredFields();
    	for (Field field : fields) {
			String name = field.getName();
			Method getMethod = clazz.getDeclaredMethod("get"+name.substring(0, 1).toUpperCase()+name.substring(1));
			String value = getMethod.invoke(obj).toString();
			params.put(name, value);
		}
    	view.setParameters(params);
    	
    	return view;
    }
}
