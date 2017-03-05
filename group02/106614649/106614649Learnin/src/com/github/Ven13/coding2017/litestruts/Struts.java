package com.github.Ven13.coding2017.litestruts;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
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
    	
    	View view = new View();
    	
    	Map map = testParseXmlData(actionName, "/struts.xml");
    	
    	String className = (String)map.get(actionName);
    	
    	String resultName = "";
        System.out.println(className);
        
        try {
			Class c = Class.forName(className);
			Object obj = c.newInstance();
			Method method = null;
			
			for (Map.Entry<String, String> entry : parameters.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				PropertyDescriptor pd = new PropertyDescriptor(key, c);
				Method setMethod = pd.getWriteMethod();
				setMethod.invoke(obj, value);
			}
			
			Method exec = c.getDeclaredMethod("execute");
			Object res = exec.invoke(obj);
			if(res != null) {
				resultName = (String)map.get(actionName + '|' + res);
				view.setJsp(resultName);
			}
			Field[] fields = c.getDeclaredFields();
			for(Field f : fields){
				PropertyDescriptor descriptor = new PropertyDescriptor(f.getName(), c);
				Method getMethod = descriptor.getReadMethod();
				Object value = getMethod.invoke(obj);
				parameters.put(f.getName(), (String)value);
			}		
			view.setParameters(parameters);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
    	
    	
    	return view;
    } 
    
    public static Document parse2Document(String xmlFilePath){
        SAXReader reader = new SAXReader();
        Document doc = null;
        try {
        	InputStream inputStream = Class.class.getResourceAsStream(xmlFilePath);
            doc = reader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return doc;
    }
    
    public static Map testParseXmlData(String actionName, String xmlFilePath){
        //获取xml解析器对象
        //SAXReader reader = new SAXReader();
        //将xml解析为Document对象
        Document doc = parse2Document(xmlFilePath);
        //获取文档的根元素
        Element root  = doc.getRootElement();
        
        //定义保存属性、值的map
        Map<String,String> map = new HashMap<String,String>();
        
        
        //遍历当前元素(在此是根元素)的子元素
        
        for (Iterator i_pe = root.elementIterator(); i_pe.hasNext();) {
        	
	        Element e_pe = (Element) i_pe.next();
	        //获取当前元素的名字
	        String act = e_pe.getName();
	        //获取当前元素的name和class属性的值并分别赋给attName,attClass变量
	        System.out.println(act);
	        String attName = e_pe.attributeValue("name");
	        String attClass = e_pe.attributeValue("class");
	        if (attName.equals(actionName)) {
		        map.put(attName, attClass);
		        
		        //Element e_res = e_pe.element("result");
		        //System.out.println(e_res.getName());
		        for (Iterator i_res = e_pe.elementIterator(); i_res.hasNext();) {
		        	
			        Element e_re = (Element) i_res.next();
			        //获取当前元素的名字
			        String person_n = e_re.getName();
			        //获取当前元素的name和class属性的值并分别赋给attName,attClass变量
			        System.out.println(person_n);
			        String resName = e_re.attributeValue("name");
			        String resClass = e_re.getStringValue();
			        map.put(attName + '|' + resName, resClass);
			        
		        }
	        }
        }
        
        return map;
        
    }

}
