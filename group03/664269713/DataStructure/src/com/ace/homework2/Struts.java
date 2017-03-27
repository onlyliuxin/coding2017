package com.ace.homework2;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {
	/*
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
	 * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp， 放到View对象的jsp字段中。
	 */
	private static final String STRUTS_XML = "struts.xml";
	private static final String NAME = "name";
	private static final String CLASS = "class";
	private static final String EXECUTE = "execute";
	private static final String GET = "get";
	private static final String SET = "set";

	private static List<StrutsObj> getStrutsList() {
		List<StrutsObj> strutsObjList = new ArrayList<StrutsObj>();
		URL path = Struts.class.getResource(STRUTS_XML);
		File f = new File(path.getFile());
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(f);
			Element strutsNode = document.getRootElement();
			Iterator actionIterator = strutsNode.elementIterator();
			while (actionIterator.hasNext()) {
				Element actionNode = (Element) actionIterator.next();
				StrutsObj strutsObj = new StrutsObj();
				strutsObj.setName(actionNode.attributeValue(NAME));
				strutsObj.setClassName(actionNode.attributeValue(CLASS));

				Iterator resultIterator = actionNode.elementIterator();
				Map<String, String> map = new HashMap<String, String>();
				while (resultIterator.hasNext()) {
					Element resultNode = (Element) resultIterator.next();
					map.put(resultNode.attributeValue(NAME), resultNode.getStringValue());
				}
				strutsObj.setMap(map);
				strutsObjList.add(strutsObj);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return strutsObjList;
	};

	public static View runAction(String actionName, Map<String, String> parameters) {
		List<StrutsObj> lists = getStrutsList();
		View view = new View();
		for (int i = 0; i < lists.size(); i++) {
			StrutsObj strutsObj = lists.get(i);

			if (actionName.equals(strutsObj.getName())) {

				try {
					Class clazz = Class.forName(lists.get(i).getClassName());
					Object obj = clazz.newInstance();
					for (Map.Entry<String, String> entry : parameters.entrySet()) {
						String methodName = SET + entry.getKey().substring(0, 1).toUpperCase()
								+ entry.getKey().substring(1);
						clazz.getMethod(methodName, String.class).invoke(obj, entry.getValue());
					}

					String status = (String) clazz.getMethod(EXECUTE).invoke(obj);
					Map<String, String> strutsMap = strutsObj.getMap();
					for (Map.Entry<String, String> entry : strutsMap.entrySet()) {
						if (entry.getKey().equals(status)) {
							view.setJsp(entry.getValue());
							break;
						}
					}

					Map<String, String> map = new HashMap<String, String>();
					Method[] methods = clazz.getDeclaredMethods();
					for (Method method : methods) {
						if (method.getName().startsWith(GET)) {
							String tempString = method.getName().substring(3);
							String resultkey = tempString.substring(0, 1).toLowerCase() + tempString.substring(1);
							String resultValue = (String) method.invoke(obj);
							map.put(resultkey, resultValue);
							break;
						}

					}
					view.setParameters(map);
				} catch (ClassNotFoundException e) {
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
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return view;
	}
	/*URL path = Struts.class.getResource(STRUTS_XML);
	File f = new File(path.getFile());
	SAXReader saxReader = new SAXReader();
	View view = new View();
	try {
        Document document = saxReader.read(f);
        Element strutsNode = document.getRootElement();
        Iterator actionIterator = strutsNode.elementIterator();
        while(actionIterator.hasNext()){
        	Element actionNode = (Element)actionIterator.next();
        	
        	if(actionName.equals(actionNode.attributeValue("name"))){
        		String className = actionNode.attributeValue("class");
	    		Class clazz = Class.forName(className);
	    		LoginAction loginAction = (LoginAction) clazz.newInstance();
		    	for(Map.Entry<String, String> entry:parameters.entrySet()){
		    		String methodName = "set" + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1);
		    		clazz.getMethod(methodName, String.class).invoke(loginAction, entry.getValue());
		    	}
		    	
		    	String status = (String) clazz.getMethod("execute").invoke(loginAction);
		    	
		    	Map<String,String> map = new HashMap<String,String>();
		    	
		    	Method[] methods = clazz.getDeclaredMethods();
		    	for(Method method: methods){
		    		if(method.getName().startsWith("get")){
		    			String tempString = method.getName().substring(3);
		    			String resultkey = tempString.substring(0, 1).toLowerCase() + tempString.substring(1);
		    			String resultValue = (String)method.invoke(loginAction);
		    			map.put(resultkey, resultValue);
		    		}
		    		
		    	}
		    	
		    	view.setParameters(map);
		    	Iterator resultIterator = actionNode.elementIterator();
	        	while(resultIterator.hasNext()){
	        		Element resultNode = (Element) resultIterator.next();
	        		if(status.equals(resultNode.attributeValue("name"))){
	        			view.setJsp(resultNode.getStringValue());
	        			return view;
	        		}
	        	}
        	} 
        }
    } catch (DocumentException e) {
        System.out.println(e.getMessage());
    } catch (ClassNotFoundException e) {
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
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/

}