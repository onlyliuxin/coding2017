package com.m0305.lisestruts;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.util.Dom4JforXML;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters){

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
    	Document document=null;
		try {
			document = reader.read(Struts.class.getResource("struts.xml"));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Element root=document.getRootElement();
    	/*
    	 * 当前节点的名称：struts
    	 * 
				当前节点的名称：action
				属性name:login
				属性class:com.m0305.lisestruts.LoginAction
				
					当前节点的名称：result
					属性name:success
					result：/jsp/homepage.jsp
					
					当前节点的名称：result
					属性name:fail
					result：/jsp/showLogin.jsp
					
			<struts>
			    <action name="login" class="com.m0305.lisestruts.LoginAction">
			        <result name="success">/jsp/homepage.jsp</result>
			        <result name="fail">/jsp/showLogin.jsp</result>
			    </action>
			    <action name="logout" class="com.m0305.lisestruts.LogoutAction">
			    	<result name="success">/jsp/welcome.jsp</result>
			    	<result name="error">/jsp/error.jsp</result>
			    </action>
			</struts>
			
    	 */
    	String xpath = "//action[@name='" + actionName + "']/child::*";  
    	String xpath1 = "//action[@name='" + actionName + "']";  
    	
    	List list1=root.selectNodes(xpath1);
    	
    	String className=null;
    	String methodName=null;
    	if(!list1.isEmpty()){
    		Element elt = (Element) list1.get(0);  
    		Attribute classattr=elt.attribute("class");
    		Attribute methodattr=elt.attribute("method");
    		className=classattr.getValue();
    		if(methodattr!=null){
    			methodName=methodattr.getValue();
    		}
    	}
    	Class clazz=null;
    	try {
			clazz=Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	Object act=null;
    	try {
			act=clazz.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
    	for(Entry<String, String> s:parameters.entrySet()){
    		s.getKey();
    		try {
				Method m1=clazz.getDeclaredMethod(param2methodname(s.getKey()), String.class);//???
				m1.invoke(act, s.getValue());//设置参数的值
				
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
    	}
    	//调用execute方法后，读取所有getter方法，将值放到view的param里面去
    	String jspkey=null;
    	View view=new View();
    	Map viewParams=new HashMap<String,Object>();
    	if(methodName==null){
    		methodName="execute";
    	}
    	try {
			Method defaultmethod=clazz.getDeclaredMethod(methodName);
			jspkey=defaultmethod.invoke(act).toString();//action返回值
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Method[] methods=clazz.getDeclaredMethods();
    	for(Method method:methods){
    		if(method.getName().startsWith("get")){
    			try {
					viewParams.put(removeGet(method.getName()), method.invoke(act));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	view.setParameters(viewParams);
    	//读xml文件里面的result，根据返回值决定哪个jsp，放到view里面的jsp中
    	
    	
    	//读result里面的值
    	List list=root.selectNodes(xpath);
    	Iterator it = list.iterator();  
        while (it.hasNext()) {  
            Element elt = (Element) it.next();  
            Attribute attr = elt.attribute("name");  
            if(jspkey.equals(attr.getValue())){
            	view.setJsp(elt.getStringValue());
            	break;
            }
        }  
    	return view;
    }    
    public static String param2methodname(String name){
    	//password change to setPassword
    	
    	return "set"+name.substring(0, 1).toUpperCase() + name.substring(1);
    }
    public static String removeGet(String name){
		String name1=name.substring(3);
		String result=name1.substring(0, 1).toLowerCase()+name1.substring(1);
		return result;
	}

}















