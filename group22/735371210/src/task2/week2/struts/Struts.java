package task2.week2.struts;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {

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
    	String jsp="";
    	
    	Map<String,String> viewParams=new HashMap<String,String>();
    	
    	Element ele=getNode(actionName);
    	
    	String className=ele.attributeValue("class");
    	
    	Class actionClass=Class.forName(className);
    	
    	Object action=actionClass.newInstance();
    	
    	for(Map.Entry<String, String> entry :parameters.entrySet()){
    		
    		
    		Method m=actionClass.getMethod("set"+entry.getKey().substring(0,1).toUpperCase()+entry.getKey().substring(1), String.class);
    		
    		m.invoke(action, entry.getValue());
    		
    		
    	}
    	
    	Method execute =actionClass.getMethod("execute");
    	String result=(String)execute.invoke(action);

    	System.out.println(result);
    	
    	Method[] methods=actionClass.getDeclaredMethods();
    	
    	for(Method m : methods){
    		if(m.getName().startsWith("get")){
    			String value =(String) m.invoke(action);
    			
    			String key=m.getName().substring(3);
    			key=key.substring(0,1).toLowerCase()+key.substring(1);
    			
    			viewParams.put(key,value);
    			
    			
    		}
    	}
    	
    	for(Element e:(List<Element>) ele.elements("result")){
    		
    		if(e.attributeValue("name").equals(result)){
    			
    			 jsp=e.getText();
    		}
    		
    	}
    	
    	View view=new View();
    	view.setJsp(jsp);
    	
    	view.setParameters(viewParams);
    	
    	
    	return view;
    }    
    
    
    public static Element getNode(String actionName){
    	Element ele=null;
    	
    	SAXReader reader =new SAXReader();
		
		InputStream in =Struts.class.getResourceAsStream("struts.xml");
		
		try {
			Document doc =reader.read(in);
			Element root =doc.getRootElement();
			
	    	List<Element> childNode= root.elements();
			
			
			for(Element e:childNode){
			
				if(e.attributeValue("name").equals(actionName)){
					ele=e;
					break;
				}
			
			}
		
			
		} catch (DocumentException ex) {
			
			ex.printStackTrace();
		}
			
		return ele;
	
		
    	
    }
    
    

}

