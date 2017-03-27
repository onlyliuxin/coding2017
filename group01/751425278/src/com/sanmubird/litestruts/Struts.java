package com.sanmubird.litestruts;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;



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
    	String actionClassPath = null ; 
    	Class actionClass; 
    	String resultName = null;
    	List<Element> actionElement =   parseXML();
    	try {
	    	for(Element e : actionElement){
	    		if("action".equals(e.getName())){
	    			if(e.attributeValue("name").equals(actionName)){
	    				actionClassPath = e.attributeValue("class");
						actionClass = Class.forName(actionClassPath);
		    			Object actionClassInstance = actionClass.newInstance();	//通过反射 得到类的对象
		    			
		    			if(parameters != null){
		    				for(Map.Entry<String, String> entry : parameters.entrySet()){
		    					String filedName = entry.getKey();
		    					String methodName = "set"+toUpperFirstLetter(filedName);
		    					Class<?> filedType = actionClass.getDeclaredField(filedName).getType();
		    					Method method = actionClass.getDeclaredMethod(methodName, filedType);
		    					method.invoke(actionClassInstance, entry.getValue());//对当前对象的实例化;
		    				}
		    			}
		    			
		    			Method execute = actionClass.getDeclaredMethod("execute");
		    			String result = (String) execute.invoke(actionClassInstance); //得到execute执行的结果
		    			
		    			Method[] methods = actionClass.getDeclaredMethods();
		    			Map<String,Object> param  = new HashMap<String,Object>();
		    			for(Method method : methods){
		    				String methodName = method.getName();
		    				if(method.getName().startsWith("get")){
		    					String filedName = methodName.substring(3,4).toLowerCase()+methodName.substring(4);
		    					Object filedValue = method.invoke(actionClassInstance);
		    					param.put(filedName, filedValue);
		    				}
		    			}
		    			
		    			view.setParameters(param);
		    			List<Element> resultElement = e.elements();
		    			for(Element e1 : resultElement ){
		    				if("result".equals(e1.getName())){
		    					resultName = e1.attributeValue("name");
		    					if(resultName.equals(result)){
		    						view.setJsp(e1.getText());
		    						break;
		    					}
		    				}
		    			}
	    			}
	    		}
	    	} 
    	}catch (Exception e1) {
				e1.printStackTrace();
		}
    	return view ;
    }    
    
    public static List<Element> parseXML(){
    	SAXReader reader = new SAXReader();
    	List<Element> firstList = null;
    	try {
			
			InputStream in = Struts.class.getClassLoader().getResourceAsStream("com/sanmubird/litestruts/struts.xml");//  要加载的文件和.class文件在同一个目录下;
			// Class.getClassLoader.getResourceAsStream(String path);  默认是从ClassPath根下获取的;
			// Class.getResourceAsStream(String path) path(/dirName/fileName)是从ClassPath根下获取的,没有/则是从本.class文件同目录中获取的;
			Document doc = reader.read(in);
			Element root = doc.getRootElement();
			firstList = root.elements();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return firstList;
    }

    public static String toUpperFirstLetter(String s){
    	if(s != "" ){
    		String s1 = s.substring(0,1).toUpperCase();
    		String other = s.substring(1);
    		return s1+other;
    	}else{
    		throw new RuntimeException("传入的参数不能是空 或 '' ");
    	}
    }
    
}
