package com.coderising.action;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Struts {
	
	
	
	public static void main(String[] args) throws DocumentException {
         String actionName = "login";
        
		Map<String,String> params = new HashMap<String,String>();
        params.put("name","test");
        params.put("password","1234");      
        View view  = Struts.runAction(actionName,params);  
        System.out.println(view.getJsp());
        System.out.println(view.getParameters());
	}
	
 
    public static View runAction(String actionName, Map<String,String> parameters)  {
    	 View view = new View();
    	    SAXReader reader = new SAXReader();  
	      //读取文件 转换成Document  
	        org.dom4j.Document document;
		   try {
				document = reader.read(new File("src/struts.xml"));
				Element root = document.getRootElement();  
				@SuppressWarnings("unchecked")
				List<Element> elements = root.elements();
				for (Element element : elements) {
		            Attribute actionAttribute = element.attribute("name");
		            Attribute classAttribute = element.attribute("class");      
		            if(actionName.equals(actionAttribute.getValue())){	            	
		            	 String clazz = null;	 	    		
	 	    			 clazz = classAttribute.getValue();
	 	    			 Object o = Class.forName(clazz).newInstance();
	 	    			for (Map.Entry<String,String> entry : parameters.entrySet()) {  
	 	    				String name = entry.getKey();
	 	    				String value =entry.getValue();
	 	    				String methodname = "set"+name.substring(0,1).toUpperCase()+name.substring(1);
	 	    				Method m = o.getClass().getMethod(methodname, String.class);
	 	    				m.invoke(o, value);
	 	    			  
	 	    			} 
						Method m3 = o.getClass().getMethod("execute");					
						String result = (String) m3.invoke(o);	
						String jspPath = null;
						List<Element> element1s = element.elements("result");
						if(result.equals("success")){		            	
			            	for (int i = 0; i < element1s.size(); i++) {
			            		Attribute attribute2 = element1s.get(i).attribute("name");
			            		if (attribute2.getValue().equals("success")) {
									jspPath = element1s.get(i).getStringValue();
								}	            		
							}	            	
						}else if(result.equals("fail")){
							for (int i = 0; i < element1s.size(); i++) {
			            		Attribute attribute2 = element1s.get(i).attribute("name");
			            		if (attribute2.getValue().equals("fail")) {
									jspPath = element1s.get(i).getStringValue();
								}
						}
		        }
						HashMap<String, Object>viewparamterHashMap = new HashMap<String, Object>();
						Method[]methods = o.getClass().getMethods();
					    String  methodname;
						for (int j = 0; j < o.getClass().getMethods().length; j++) {
							methodname = methods[j].getName();								
							if(methodname.startsWith("get")&&!methodname.equals("getClass")){
								String methodname1 = methods[j].getName();
								methodname1 = methodname.substring(3,4).toUpperCase()+methodname1.substring(4);
								viewparamterHashMap.put(methodname1, methods[j].invoke(o));								
							}
						}						
						  view.setJsp(jspPath);		
						  view.setParameters(viewparamterHashMap);
						  return view;						
				}	   
	    }
		} catch (Exception e) {
			// TODO: handle exception
		}		
		   return null;
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
    	
    	
    }			   

}
