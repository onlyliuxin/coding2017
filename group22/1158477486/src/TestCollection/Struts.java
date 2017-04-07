package TestCollection;

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
    	
    	View view = new View();
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
		放到View对象的jsp字段中。
        */
    	
    	SAXReader reader = new SAXReader();
    	Document document = null;
		try {
			document = reader.read("src/struts.xml");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root= document.getRootElement();
		List<Element> list = root.elements("action");
		String className = null;
		Element newElement = null;
		for (Element element : list) {
			if(element.attribute("name").getValue().equals(actionName)){
				Attribute  attribute = 	element.attribute("class");
				newElement = element;
				className = attribute.getValue();
			}
		}
		Class clazz = null;
		try {
			clazz = Class.forName(className);
			Object obj = clazz.newInstance();
		for (String key : parameters.keySet())
		{
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				if(method.getName().toLowerCase().equals(("set"+key).toLowerCase())){
					method.invoke(obj,parameters.get(key));
				}
			}
			
		}
		
		String value = (String) clazz.getMethod("execute").invoke(obj);
		List<Element> elements = newElement.elements();
		
		String message = "";
		String jsp = "";
		for (Element element : elements) {
			if(element.attribute("name").getValue().equals(value)){
				jsp = element.getText();
			}
		}
		if("success".equals(value)){
			message = "login successful";
		}else if("fail".equals(value)){
			message = "login failed,please check your user/pwd";
		}
		view.setJsp(jsp);
		Map<String,String> p = new HashMap<String, String>();
		p.put("message",message);
		view.setParameters(p);
		
		return view;
		
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
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return view;
    }    
}