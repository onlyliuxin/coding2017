package org.coding.two;	

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Struts {
	
    private static final String METHOD_SET = "set";
	private static final String METHOD_GET = "get";
	private static Map<String, ActionBeanDefinition> actionDefinitionMap =  new HashMap<String, ActionBeanDefinition>();
    
    static {
    	//0
    	parseXml();
    }
    
    @SuppressWarnings("unchecked")
	public static void parseXml(){
    	SAXReader reader = new SAXReader();
    	Document document = null;
		String path = Struts.class.getClassLoader().getResource("").getPath();
		try {
			document = reader.read(path + ActionBeanDefinition.DEFAULT_RESOURCE);
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
    	Element struts = document.getRootElement();
    	List<Element> actions = struts.elements(ActionBeanDefinition.TAG_ACTION);
    	for (Element element : actions) {
    		String name = element.attributeValue(ActionBeanDefinition.TAG_ACTION_ATTR_NAME);
    		String className = element.attributeValue(ActionBeanDefinition.TAG_ACTION_ATTR_CLASS);
    		List<Element> results = element.elements(ActionBeanDefinition.TAG_RESULT);
    		ActionBeanDefinition beanDefinition = new ActionBeanDefinition(name, className);
    		for (Element result : results) {
    			beanDefinition.putResult(result.attributeValue(ActionBeanDefinition.TAG_RESULT_ATTR_NAME), result.getTextTrim());
			}
    		actionDefinitionMap.put(name, beanDefinition);
		}
//    	System.out.println(actionDefinitionMap);
    }

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
    	
    	if(!actionDefinitionMap.containsKey(actionName)) {
    		throw new RuntimeException("does not exist action : " + actionName);
    	}
    	ActionBeanDefinition beanDefinition = actionDefinitionMap.get(actionName);
    	try {
    		//1
			Class<?> bean = Class.forName(beanDefinition.getClassName());
			Object instance = bean.newInstance();
			Set<String> keySet = parameters.keySet();
			for (String key : keySet) {
				Method method = bean.getMethod(getSetMethodName(key), new Class[]{String.class});
				method.invoke(instance, parameters.get(key));
			}
//			System.out.println(instance);
			//2
			Method method = bean.getMethod("execute");
			Object result = method.invoke(instance);
			
			//3
			Method[] methods = bean.getMethods();
			Map<String, Object> parameterss = new HashMap<String, Object>(); 
			for (Method m : methods) {
				String methodName = m.getName();
				if(methodName.contains(METHOD_GET)) {
					String para = getPropNameByMethoedName(methodName);
					parameterss.put(para, m.invoke(instance));
				}
			}
			View view = new View();
			view.setParameters(parameterss);
//			System.out.println(parameterss);
			//4
			String jsp = actionDefinitionMap.get(actionName).getResultMap().get(result);
			view.setJsp(jsp);
			return view;
			
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
    	
    	
    	return null;
    }    
    

    private static String getPropNameByMethoedName(String methodName) {
    	String prop = null;
    	if(methodName.contains(METHOD_GET)){
    		String s = methodName.substring(3);
    		prop = s.substring(0, 1).toLowerCase() + s.substring(1);
    	}
		return prop;
	}

	private static String getSetMethodName(String para) {
		return METHOD_SET + para.substring(0, 1).toUpperCase() + para.substring(1);
	}
}
