package struts_Reflect;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
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
        
        *
        */
    	
    	//创建变量保存解析到的className的字符串
    	String className = null;
    	
    	//---------解析XML文件---------------------------------------------------------------------------
    	
    	 // 创建saxReader对象  
        SAXReader reader = new SAXReader();  
        // 通过read方法读取一个文件 转换成Document对象  
        Document document = reader.read(new File("src/struts_Reflect/Struts.xml"));  
        //获取根节点元素对象  
        Element root = document.getRootElement();  
        //System.out.println("Root: " + root.getName());
           
        // 迭代器，获取元素name属性值为“login”的class属性的值，保存到变量className中。
        for (Iterator iter = root.elementIterator(); iter.hasNext();)
        {
            Element e = (Element) iter.next();
            String name = e.attributeValue("name");
            
            if(name.equals("login"))
            {
            	className = e.attributeValue("class");
            }
                        
        }
        
        //-----------反射获取LoginAction类的实例并调用其方法---------------------------------------------------------
        
        //获取实例
        Class clazz = Class.forName("struts_Reflect.LoginAction");
        Object obj = clazz.newInstance();
        
        //调用setName 和 setPassword方法
        Method mSetName = clazz.getMethod("setName", String.class);
        Method mSetPassWord = clazz.getMethod("setPassword", String.class);
        mSetName.invoke(obj, parameters.get("name"));
        mSetPassWord.invoke(obj, parameters.get("password"));
        
        //调用excute方法
        Method mExecute = clazz.getMethod("execute", null);
        String result = (String) mExecute.invoke(obj, null);      
        System.out.println(result);
        
        //找到所有getter方法，属性和值放到parameterMap中。
        Method[] methods = clazz.getDeclaredMethods();
//        HashMap<String, String> paraMap = new HashMap<String, String>();
        
    	ArrayList<String> al = new ArrayList<String>();
    	al.add("name");
    	al.add("password");
    	al.add("message");
    	String key = null;
    	String value = null;
    	Field field = null;
    	
    	for(int i = 0; i < al.size(); i++)
    	{
    		key = al.get(i);
    		field = clazz.getDeclaredField(key);
    		field.setAccessible(true);
    		value = (String) field.get(obj);
    		parameters.put(key, value);
    		System.out.println(key+"---"+value);
    	}
    	
    	
    	
    	View view = new View();
    	view.setParameters(parameters);
    	
    	//----------将JSP字段放到View对象中-------------------------------------
    	
    	//获取名称为action的元素节点
    	Element actionE = root.element("action");
    	
    	// 迭代器，获取元素result中的文字
    	String rValue = null;
    	
        for (Iterator iter = actionE.elementIterator(); iter.hasNext();)
        {
            Element e = (Element) iter.next();
            String name = e.attributeValue("name");
            
            if(name.equals(result))
            {
            	rValue = e.getText();
            	view.setJsp(rValue);
            	System.out.println(rValue);
            }
                        
        }
   
    	return view;
 
    }  

}