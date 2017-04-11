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


public class Struts {
	/*
	 * 0. ��ȡ�����ļ�struts.xml
	 * 
	 * 1. ����actionName�ҵ����Ӧ��class �� ����LoginAction, ͨ������ʵ��������������
	 * ��parameters�е����ݣ����ö����setter������ ����parameters�е������� ("name"="test" ,
	 * "password"="1234") , �Ǿ�Ӧ�õ��� setName��setPassword����
	 * 
	 * 2. ͨ��������ö����exectue ������ ����÷���ֵ������"success"
	 * 
	 * 3. ͨ�������ҵ����������getter���������� getMessage��, ͨ�����������ã� ��ֵ�������γ�һ��HashMap , ����
	 * {"message": "��¼�ɹ�"} , �ŵ�View�����parameters
	 * 
	 * 4. ����struts.xml�е� <result> ����,�Լ�execute�ķ���ֵ�� ȷ����һ��jsp�� �ŵ�View�����jsp�ֶ��С�
	 */
	private static final String STRUTS_XML = "struts.xml";
	private static final String NAME = "name";
	private static final String CLASS = "class";
	private static final String EXECUTE = "execute";
	private static final String GET = "get";
	private static final String SET = "set";

	private static List<StrutsObj> getStrutsList() {
		/*List<StrutsObj> strutsObjList = new ArrayList<StrutsObj>();
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

		return strutsObjList;*/
		return null;
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