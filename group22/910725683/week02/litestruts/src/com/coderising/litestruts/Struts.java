package com.coderising.litestruts;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.Node;
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
    	
    	try{
    		//读取strut.xml
            SAXReader saxReader = new SAXReader();        
            Document document = saxReader.read("struts.xml");     
    		
	        try {
	        	//XPath解析DOM树，获得actionName对应的类名称（XPath在爬虫里也非常好用）
	        	Node actionClass = document.selectSingleNode("/struts/action[@name=\"" + actionName + "\"]/@class");
	        	Class<?> classtype = Class.forName(actionClass.getText());
	        	Object obj = classtype.newInstance();  
	        	
	        	//按照parameters设置实例变量
		        for (Entry<String, String> entry : parameters.entrySet()){
		        	 setter(obj,entry.getKey(),entry.getValue(),String.class);
		        }
		    	try{
		    		//执行execute方法
		    		Method met = classtype.getMethod("execute");
			    	String result = (String) met.invoke(obj);
			    	//获得执行后实例变量的值
			    	HashMap<String,String> hashmap = new HashMap<String, String>();
			    	for(Method m : classtype.getMethods() ) { 
			    		String methodName = m.getName();
			    		if (methodName.startsWith("get")){
			    			hashmap.put(methodName.substring(3,4).toLowerCase() + methodName.substring(4), m.invoke(obj).toString());
			    		}	
			    	}
			    	view.setParameters(hashmap);
			    	//获得执行结果对应的jsp
			    	Node jsp = document.selectSingleNode("/struts/action[@name=\"" + actionName + "\"]/result[@name=\"" + result + "\"]");
			    	view.setJsp(jsp.getText());

		    	}
			    catch(Exception e){
			    	e.printStackTrace();
			    }        
		    }
		    catch (Exception e){
		    	e.printStackTrace();
		    }	   	
	    }catch (Exception e) {
	        e.printStackTrace();
	    }
    	return view;
	}
    
    //按照参数名运行setter方法
    private static void setter(Object obj,String att,Object value,Class<?> type){  
        try{  
            Method met = obj.getClass().getMethod("set"+initStr(att),type); 
            met.invoke(obj,value) ;
        }catch(Exception e){  
            e.printStackTrace() ;  
        }  
    } 
    
    //小写驼峰命名法
    private static String initStr(String str){
        char[] ch = str.toCharArray();  
        if (ch[0] >= 'a' && ch[0] <= 'z') {  
            ch[0] = (char) (ch[0] - 32);  
        }  
        return new String(ch);  
    }
   
}
