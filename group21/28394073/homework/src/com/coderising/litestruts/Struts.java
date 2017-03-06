package com.coderising.litestruts;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.w3c.dom.css.Rect;





public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) throws DocumentException, ClassNotFoundException, ReflectiveOperationException, InstantiationException {

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
    	String classValue = null;//为什么一定要初始化null?
    	
    	//0.Open,read file and get corresponding class
    	File file = new File("src/com/coderising/litestruts/struts.xml");
    	SAXReader reader = new SAXReader();  
        Document doc = reader.read(file);
        Element root = doc.getRootElement();
        for (Iterator iter = root.elementIterator("action"); iter.hasNext();){
        	Element element = (Element) iter.next();
        	Attribute nameAttr = element.attribute("name");
        	String attrValue = nameAttr.getValue();
        	if(attrValue.equals(actionName)){
        		Attribute classAttr = element.attribute("class");
        		classValue= classAttr.getValue();
        		System.out.println(classValue);
        	}//end if
        }//end for
        
        //1.
        Class<?> reflectClass = Class.forName(classValue);
//	    System.out.println(reflectClass.getName());
        Object obj = reflectClass.newInstance();
        Field nameField = reflectClass.getDeclaredField("name");
        Field passwordField = reflectClass.getDeclaredField("password");
        nameField.setAccessible(true);
        passwordField.setAccessible(true);
        nameField.set(obj, parameters.get("name"));
        passwordField.set(obj, parameters.get("password"));
//      System.out.println(nameField.get(obj));
//      System.out.println(passwordField.get(obj));
        
        //2.
        Method method = reflectClass.getMethod("execute");
        String result = method.invoke(obj).toString();
        
        //3.
        Field msgField = reflectClass.getDeclaredField("message");
        msgField.setAccessible(true);
        String msgValue = msgField.get(obj).toString();
//      System.out.println(msgField.get(obj));
        Map<String,String> viewParameters = new HashMap<String,String>();
        viewParameters.put("message", msgValue);
        
        View view = new View();
        view.setParameters(viewParameters);
        
        //4.
        String s1 = "//action[@name="+"'"+actionName+"'"+"]"+"/result[@name="+"'"+result+"'"+"]";
        System.out.println(s1);
        Element node = (Element)doc.selectSingleNode(s1);
        System.out.println(node.getText());
        view.setJsp(node.getText());
        
        return view;

    }
    
    public static void main(String[] args) throws Exception{
    	String actionName="login";
    	Map<String,String> parameters = new HashMap<String, String>();
    	parameters.put("name", "test");
    	parameters.put("password", "1234");
		Struts.runAction(actionName, parameters);
    }
    
}