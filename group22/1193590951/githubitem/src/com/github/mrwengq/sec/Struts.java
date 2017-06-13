package com.github.mrwengq.sec;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
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
public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {
    	View view = new View();
    	//0.读取xml文件
    	Document doc = createDOC("struts.xml");  
    	Class actionClass= queryClass(actionName, doc); 
    	//1.实例化对象调用set方法
    	Set<String> paramNames = parameters.keySet(); //取出调用方法所用参数名称
    	Iterator<String> iter = paramNames.iterator();
    	Object ob = null;
    	 try {
			ob = actionClass.newInstance();
			while(iter.hasNext()){
				String temp = iter.next();
				String methodName = "set"+temp.replaceFirst( temp.substring(0,1),temp.substring(0,1).toUpperCase());//方法名称
				String methodParam = parameters.get(temp);  //方法参数
				Method me= actionClass.getMethod(methodName,String.class);
				me.invoke(ob,methodParam);				
			}
			//2.调用execute反方法取出返回值
			String actionMethod = (String)actionClass.getMethod("execute").invoke(ob);
			//3.调用所有的get，并保存返回值
			Map map = new HashMap();
			Method[] methods = actionClass.getMethods(); 
			for(Method method : methods){
				String methodName = method.getName();
				if(methodName.substring(0,3).equals("get")){
					Object value = method.invoke(ob);
					String key = methodName.substring(3,methodName.length()).toLowerCase();
					map.put(key, value);
				}
			}
			view.setParameters(map);//将数据保存到view中
			//4.设置返回结果视图
			String jsp = queryResult(actionName,doc,actionMethod);
			view.setJsp(jsp);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	 
    	return view;
    }
    private static String queryResult(String actionName,Document doc,String actionMethod){
    	String reView = null; 
    	Element el = queryActionElement(actionName,doc);//查找action元素
    	List<Element> resultEl =  el.elements("result");
    	for(Element rel : resultEl){
    		Attribute att = rel.attribute("name");
    		if(att.getValue().equals(actionMethod)){
    			reView = rel.getText();
    		}
    	}
    	return reView;
    }
    //查找对应的action元素
    private static Element queryActionElement(String actionName, Document doc){
    	Element root = doc.getRootElement();
    	Element actionElement = null;
    	List<Element> list = root.elements("action");
    	for(Element el : list){
    		Attribute att = el.attribute("name");
    		if(att.getValue().equals(actionName)){
    			actionElement = el;
    		}
    	}
		return actionElement;
    	
    }
    //查找action类
	private static Class queryClass(String actionName, Document doc) {
		Element el = queryActionElement(actionName,doc);//查找action元素
    	Attribute att = el.attribute("class");
    	String className = att.getValue();
    		
    	Class actionClass  = null;
    	try {
			 actionClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	return actionClass;
	}  
	//获取document对象
    private static Document createDOC(String fileName){
    	
    	//使用dom4j的读取xml文件
    	SAXReader sr = new SAXReader();
    	URL fileUrl = Struts.class.getResource(fileName);
    	Document doc = null;
    	try {
    		doc = sr.read(fileUrl);
    	} catch (DocumentException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return doc;
    }

}
