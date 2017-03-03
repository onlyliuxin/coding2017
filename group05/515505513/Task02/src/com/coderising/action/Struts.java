package com.coderising.action;
import java.beans.PropertyDescriptor;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {
     
		//0. 读取配置文件struts.xml
    	
    	SAXReader saxReader = new SAXReader();
    	View view = new View();
    	Element actionElement = null;
		String classVal="";
 		try {
			Document read = saxReader.read(new File("src/com/coderising/action/struts.xml"));
			//获得根节点
			Element rootElement = read.getRootElement();
			//获得根节点下的一级节点
			List<Element> elements = rootElement.elements();
			for (Element element : elements) {
				Attribute attribute = element.attribute("name");
				//如果等于传递进来的actionName
				if(attribute.getValue().equals(actionName)){
					actionElement = element;
					//获得属性值
					Attribute classAttr = element.attribute("class");
					classVal = classAttr.getValue();
				}
			}
			
//	 	1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象）
//			据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
//			("name"="test" ,  "password"="1234") ,     	
//			那就应该调用 setName和setPassword方法
	 		//com.coderising.action.LoginAction
			
			Class<?> clazz = Class.forName(classVal);
			Object c = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			Set<Entry<String, String>> set = parameters.entrySet();
			for (Entry<String, String> entry : set) {
				String key = entry.getKey();
				String value = entry.getValue();
				for (Field f : fields) {
					setProperty(c, key, value);
				}
			}
	
	//2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
			Method executeMethod = clazz.getDeclaredMethod("execute");
			executeMethod.setAccessible(true);
			String jspStr = "";
			String str = (String) executeMethod.invoke(c);
			List<Element> resultNameJSp = actionElement.elements();
			for (Element element : resultNameJSp) {
				Attribute attribute = element.attribute("name");
				if(attribute.getValue().equals(str)){
					jspStr = element.getText();
					break;
				}
			}
			
//			3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
//			通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
//			放到View对象的parameters
			HashMap<String,Object> hashMap = new HashMap<>();
			for (Field f : fields) {
				Object value = getProperty(c, f.getName());
				hashMap.put(f.getName(),value);
			}
			
//			4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
//			放到View对象的jsp字段中。
			view.setJsp(jspStr);
			//System.out.println("====="+view.getJsp());
			view.setParameters(hashMap);
			//System.out.println(view.getParameters().get("message"));
			
			
 		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return view;
    }    
    
    public static PropertyDescriptor getPropertyDescriper(Class clazz,String propertyName){
    	//构建一个可变字符串用来构建方法名称
    	StringBuffer sBuffer = new StringBuffer();
    	Method setMethod = null;
    	Method getMethod = null;
    	PropertyDescriptor pd = null;
    	try {
    		//根据字段名来获取字段
			Field f = clazz.getDeclaredField(propertyName);
			if(f!=null){
				//构建方法的后缀
				String methodEnd = propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
				sBuffer.append("set"+methodEnd);//构建set方法
				setMethod = clazz.getDeclaredMethod(sBuffer.toString(),new Class[]{f.getType()});
				sBuffer.delete(0, sBuffer.length());//清空整个可变字符串
				sBuffer.append("get"+methodEnd);//构建get方法
				getMethod = clazz.getDeclaredMethod(sBuffer.toString(),new Class[]{});
				//构建一个属性描述器 把对应属性 propertyName 的 get 和 set 方法保存到属性描述器中 
				pd = new PropertyDescriptor(propertyName, getMethod, setMethod);  
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pd;
    }
    
    /**
     * @param obj
     * @param propertyName
     * @param value
     */
    public static void setProperty(Object obj,String propertyName,Object value){
    	//获取对象的类型
    	Class clazz = obj.getClass();
    	PropertyDescriptor pd = getPropertyDescriper(clazz,propertyName);
    	//从属性描述器中获取set方法
    	Method setMethod = pd.getWriteMethod();
    	try {
			setMethod.invoke(obj, new Object[]{value});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
    }
    public static Object getProperty(Object obj,String propertyName){
    	Class clazz = obj.getClass();
    	PropertyDescriptor pd = getPropertyDescriper(clazz,propertyName);
    	Method getMethod = pd.getReadMethod();
    	Object value= null;
    	try {
			value = getMethod.invoke(obj,new Object[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return value;
    }
    

}
