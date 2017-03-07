package com.coderising.litestruts;

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
    	
    	SAXReader saxReader = new SAXReader();
    	View view = new View();
    	try {
			Document document = saxReader.read(new File("src/com/coderising/litestruts/struts.xml"));
			//获取根元素
			Element root = document.getRootElement();
			//根据参数获取子元素
			List<Element> listElement=root.elements();//所有一级子节点的list  
			Element actionElement = null;
			String classStr = "";
		    for(Element e:listElement){
		    	Attribute actionNameAttr = e.attribute("name");
		    	if(actionNameAttr.getValue().equals(actionName)){
		    		//获取属性值
					Attribute classAttr = e.attribute("class");
					classStr = classAttr.getValue();
					actionElement = e;
					break;
		    	}
		    }
			
			//通过反射创建对象
			Class<?> clazz = Class.forName(classStr);
			Object c = clazz.newInstance();                                       
			Field[] fields = clazz.getDeclaredFields();
			//通过set方法赋值
			Set<Entry<String, String>> set = parameters.entrySet();
			for(Entry<String, String> entry : set){
				String key = entry.getKey();
				String val = entry.getValue();
				for(Field f :fields){
					PropertyDescriptor pd = getPropertyDescriptor(clazz,key,true);
					Method setMethod = pd.getWriteMethod();
					setMethod.invoke(c, new Object[]{val});
				}
			}
			
			//通过返回值确定jsp
			String jspStr = "";
			Method executeMethod = clazz.getDeclaredMethod("execute");
			executeMethod.setAccessible(true);
			String str = (String) executeMethod.invoke(c);
			List<Element> resultElements = actionElement.elements();
			for(Element e:resultElements){
				Attribute resultNameAttr = e.attribute("name");
				if(resultNameAttr.getValue().equals(str)){
					jspStr = e.getText();
					break;
				}
		    	
		    }
			
			//获取所有的get方法,存入map
			HashMap<String,Object> map = new HashMap<String,Object>();
			for(Field f :fields){
				PropertyDescriptor pd = getPropertyDescriptor(clazz,f.getName(),false);
				Method getMethod = pd.getReadMethod();
				Object value = getMethod.invoke(c, new Object[]{});
				map.put(f.getName(), value);
			}
			
			//确定返回的View
			
			view.setJsp(jspStr);
			view.setParameters(map);
			
			
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
    	
    	return view;
    }
    
    public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String propertyName,boolean hasSetMethod) {  
        StringBuffer sb = new StringBuffer();
        Method setMethod = null;  
        Method getMethod = null;  
        PropertyDescriptor pd = null;  
        try {  
            Field f = clazz.getDeclaredField(propertyName);//根据字段名来获取字段  
            if (f!= null) {  
               //构建方法的后缀  
               String methodEnd = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);  
               if(hasSetMethod){
            	   sb.append("set" + methodEnd);
            	   setMethod = clazz.getDeclaredMethod(sb.toString(), new Class[]{ f.getType() });  
               }
               sb.delete(0, sb.length());//清空
               sb.append("get" + methodEnd);//构建get方法  
               //构建get 方法  
               getMethod = clazz.getDeclaredMethod(sb.toString(), new Class[]{ });  
               //构建一个属性描述器 把对应属性 propertyName 的 get 和 set 方法保存到属性描述器中  
               pd = new PropertyDescriptor(propertyName, getMethod, setMethod);  
            }  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
      
        return pd;  
    } 

}
