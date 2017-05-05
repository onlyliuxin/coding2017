package com.coderising.litestruts;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
    	//0. 读取配置文件struts.xml
    	SAXReader reader = new SAXReader();
    	try {
    		//0.2 读取文件
			Document doc = reader.read(new File("./src/com/coderising/litestruts/struts.xml"));
			//0.3 得到根标签
			Element rootElement = doc.getRootElement();
			//0.4 得到根标签下的所有action标签 
			Iterator<Element> elementIterator = rootElement.elementIterator("action");
			while(elementIterator.hasNext()){
				Element element = elementIterator.next();
				String nameValue = element.attributeValue("name");
				try {
					if(null != actionName && actionName.trim() != ""){
						if(actionName.equals(nameValue)){
							View view = new View();
							//进入该action标签内,结束后停止循环
							String classValue = element.attributeValue("class");
							//1.1 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）据parameters中的数据
							Class clazz =Class.forName(classValue);
							Object instance = clazz.newInstance();
							Method[] methods = clazz.getMethods();
							for (Entry<String, String> entry :  parameters.entrySet()) {
								String methodName = "set" + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1);
								//1.2调用对象的setter方法， 例如parameters中的数据是 ("name"="test" ,  "password"="1234") ,那就应该调用 setName和setPassword方法
								for (Method setterMethod : methods) {
									if(methodName.equals(setterMethod.getName())){
										setterMethod.invoke(instance,entry.getValue());
											break;
									}
								}
							}
							//2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
							Method method = clazz.getMethod("execute", null);
							Object exectueResult = method.invoke(instance, null); 
							Iterator<Element> resultElement = element.elementIterator("result");
							while(resultElement.hasNext()){
								Element result = resultElement.next();
								if(exectueResult.equals(result.attributeValue("name"))){
									String jsp = result.getText();
									view.setJsp(jsp);
									break;
								}
							}
							/*3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
								通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
								放到View对象的parameters*/
							HashMap<Object, Object> hashMap = new HashMap<>();
							for (Method getterMethod : methods) {
								if(getterMethod.getName().contains("get")){
									Object resultValue = getterMethod.invoke(instance,null);
									String resultKey = getterMethod.getName().replace("get", "").substring(0,1).toLowerCase() 
											+ getterMethod.getName().replace("get", "").substring(1);
									hashMap.put(resultKey, resultValue);
								}
							}
							view.setParameters(hashMap);
							return view;
						}
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
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
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    	return null;
    }   
    public static void main(String[] args) {
    	runAction("login",null);
	}

}
