package com.coderising.litestruts;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Key;
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

	
    public static View runAction(String actionName, Map<String,String> parameters)
    		throws ClassNotFoundException, DocumentException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException{
    	
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
    	//生成set方法的数组
    	View view = new View();
    	String[] methodNames = createSetMethodNames(parameters);
    	InputStream is=Struts.class.getResourceAsStream("/struts.xml");
    	//获取xml文件中的目标节点
    	Element element = getTargetElement(actionName);
    	//得到该节点类的名称
    	String className = element.attribute(1).getValue();
    	//获得对应的Class对象
    	Class clz =  Class.forName(className);
		
			
		
			//实例化该类
    	
			Object obj = clz.newInstance();
			//调用这个方法，该方法是通过反射实例化（创建对象）
			// 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 ("name"="test" ,
			//"password"="1234") , 那就应该调用 setName和setPassword方法
			
			invokeObjectSetter(parameters, methodNames, clz, obj);
			
			
			
			/*根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
			放到View对象的jsp字段中。
			 */
			
			setViewJsp(view, element, clz, obj);
    	
			//产生一个Map集合
			view.setParameters(createGetterMap(clz,obj));
    	
			return view;
			
			
    	
    	
    	
    	
    	
    	
    	
    
    	
    }
    	
    
    
    private static void setViewJsp(View view, Element element, Class clz,
			Object obj) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	view.setJsp(getJsp(element, executeToGetResult(clz, obj)));
		
	}

        //通过反射调用对象的exectue 方法， 并获得返回值，例如"success"

	private static String executeToGetResult(Class clz, Object obj) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
			
			//调用指定类中的excute的方法
			Method method = clz.getMethod("execute");
			String result = (String) method.invoke(obj);
			return result;
			
		
			
		
		
	}
	
//返回对应的Jsp
	@SuppressWarnings("unchecked")
	private static  String getJsp(Element element, String result) {
		
		List<Element> elements = element.elements();
		if (elements!=null){
			for (Element e : elements) {
			//如果result的值与节点的值相同则返回节点文本内容
				System.out.println(result);
			if (e!=null&&result.equals(e.attribute(0).getValue())) {
				return e.getTextTrim();
			}
		
		
            
        }
		}
		
		
		return null;
	}
	
		
	

	/* 通过反射找到对象的所有getter方法（例如 getMessage）,  
	通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
	放到View对象的parameters*/
	
	private static Map createGetterMap(Class clz, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map map = new HashMap();
		Method[] methods = clz.getMethods();
		
		
		
		for(Method item:methods){
			if (item.getName().contains("get")) {
				String key = item.getName().substring(3).toLowerCase();
				
					//调用的是get方法
					Object value = item.invoke(obj);
					System.out.println(item.invoke(obj));
					map.put(key,value);
					
			}
		}
		
		return map;
	}

	//调用set方法
    private static void invokeObjectSetter(Map<String, String> parameters,
			String[] methodNames, Class clz, Object obj) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//遍历对应的方法
    			for(String ke:methodNames){
			
				Method method = clz.getMethod(ke,String.class);
					
					//调用该方法
				String key = ke.substring(3).toLowerCase();
					method.invoke(obj, parameters.get(key));
					
					
				
			
				
			
			
			
			
			
		}
		
	}
	//获得根节点，返回需要的xml的节点
	private static Element getTargetElement(String actionName) {
		try {
		SAXReader reader = new SAXReader();
		InputStream inputStream =Struts.class.getResourceAsStream("/struts.xml");
		Document document = null;
		
			document = reader.read(inputStream);
		Element rootNode = (Element) document.getRootElement();
		List<Element> elements = ((org.dom4j.Element) rootNode).elements();
		for (Element item : elements) {
			if (actionName.equals(((org.dom4j.Element) item).attribute(0).getValue())) {
				return item;
			}
		}
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}
		return null;
	}
		//产生Set方法的数组
	private static String[] createSetMethodNames(Map<String, String> parameters) {
		String[] methodNames=new String[parameters.size()];
		int i = 0;
		for (String key:parameters.keySet()){
			//产生set方法
			methodNames[i++] = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
			
			
		}
		return methodNames;
	} 
    

}
