package com.github.ipk2015.coding2017.coderising.litestruts;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Struts {

	
    public static View runAction(String actionName, Map<String,String> parameters) throws Exception {

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
    	SAXReader reader=new SAXReader();
    	Document document = reader.read("src/com/github/ipk2015/coding2017/coderising/litestruts/struts.xml");
    	Element rootElement = document.getRootElement();
    	
    	Element actionElement=findElement(rootElement,"action","name",actionName);
    	Class<?> armClass = Class.forName(actionElement.attributeValue("class"));
    	Object newInstance = armClass.newInstance();
    
    	setParameters(armClass,newInstance,parameters);
    	
    	Method exectueMethod = armClass.getMethod("execute");
    	String exectueMethodResult = (String) exectueMethod.invoke(newInstance);
    	
    	View view=new View();
    	Map viewParameters = getViewParameters(armClass,newInstance);
    	view.setParameters(viewParameters);
    	
    	Element resultElement=findElement(actionElement,"result","name",exectueMethodResult);
    	view.setJsp(resultElement.getStringValue());
    	
    	return view;
    }    

    private static Element findElement(Element parent,String elementTag,String attributeName,String actionName){
    	Element actionElement = null;
    	String nameAttributeValue="";
    	List<Element> elements = parent.elements(elementTag);
    	for(Element element : elements){
    		nameAttributeValue = element.attributeValue(attributeName);
    		if(nameAttributeValue.equals(actionName)){
    			actionElement=element;
    			break;
    		}
    	}
    	return actionElement;
    }
    private static void setParameters(Class armClass,Object newInstance,Map<String,String> parameters) throws Exception{
    	Set<Entry<String, String>> entrySet = parameters.entrySet();
    	String entryKey="";
    	String firstLetter="";
    	Method method=null;
    	for(Entry<String,String> entry:entrySet){
    		entryKey=entry.getKey();
    		firstLetter=entryKey.substring(0, 1);
    		entryKey=entryKey.replace(firstLetter,firstLetter.toUpperCase());
    		method = armClass.getMethod("set"+entryKey, String.class);
    		method.invoke(newInstance, entry.getValue());
    	}
    }
    private static Map getViewParameters(Class armClass,Object newInstance) throws Exception{
    	Map<String,String> map=new HashMap();
    	Object getMethodReturn=null;
    	String methodName="";
    	Method[] methods = armClass.getMethods();
    	for(int i=0;i<methods.length;i++){
    		methodName=methods[i].getName();
    		if(methodName.length()>3 && methodName.startsWith("get")){
    			getMethodReturn=methods[i].invoke(newInstance);
    			map.put(methodName.substring(3).toLowerCase(), getMethodReturn.toString());
    		}
    	}
    	return map;
    }
}
