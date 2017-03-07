package com.github.orajavac.coding2017.litestructs;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;


public class Struts {
	
	private static Map<String,StrutsXml> xmlMap = new HashMap<String,StrutsXml>();

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
    	try{
			if(xmlMap.size()==0)
	    		Struts.DOM4jParserXml();
	    	
	    	StrutsXml sx = xmlMap.get(actionName);
	    	Class<?> clz = Class.forName(sx.getClassz());
	    	Object o = clz.newInstance();
	    	String key = null;
	    	Method[] mets = clz.getDeclaredMethods();
	    	for (Method m : mets){
	    		if(m.getName().startsWith("set")){
	    			key = m.getName().substring(3,m.getName().length()).toLowerCase();
	    			m.invoke(o,parameters.get(key));
	    		}
	    	}
	    	Method m1 = clz.getDeclaredMethod("execute");
	    	String result = (String)m1.invoke(o);
	    	Map<String,String> param = new HashMap<String,String>();
	    	Field[] fields = clz.getDeclaredFields();
	    	for(int i=0;i<fields.length;i++){
				PropertyDescriptor pd=new PropertyDescriptor(fields[i].getName(),clz);
				Method getMethod=pd.getReadMethod();
				param.put(fields[i].getName(), getMethod.invoke(o).toString());
	    	}
	    	View v = new View();
	    	v.setParameters(param);
	    	v.setJsp(sx.getResult().get(result));
	    	return v;
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return null;
    }
    
    @SuppressWarnings("unchecked")
	public static void DOM4jParserXml(){
    	try{
    		SAXReader reader = new SAXReader();
    		Document document = reader.read(new File("src/com/github/orajavac/coding2017/litestructs/struts.xml"));
    		Element root = document.getRootElement();
			Iterator<Element> iterator = root.elementIterator();
    		while(iterator.hasNext()){
    			Element e = iterator.next();
    			StrutsXml sx = new StrutsXml();
    			sx.setName(e.attribute("name").getValue());
    			sx.setClassz(e.attribute("class").getValue());
    			xmlMap.put(e.attribute("name").getValue(), sx);
    			Iterator<Element> r = e.elementIterator("result");
    			while(r.hasNext()){
    				Element child = r.next();
    				sx.getResult().put(child.attribute("name").getValue(), child.getTextTrim());
    			}
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
