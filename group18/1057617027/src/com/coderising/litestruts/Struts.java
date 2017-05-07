package com.coderising.litestruts;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import com.coderising.util.ReadXmlUtil;
import com.coding.basic.List;





public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {
    	

    	ReadXmlUtil read = new ReadXmlUtil();
    	Document doc = read.getDocumentByFileName("Struts.xml");
    	Element root = doc.getRootElement();
    	String attrVal = null;
    	String jsp = null;
    	View retvie= null;
    	for(Iterator it = root.elementIterator();it.hasNext();){
    		 Element element = (Element) it.next(); // 获取标签对象
             // 获取该标签对象的属性
             Attribute clas = element.attribute("class");
             Attribute name = element.attribute("name");
             if(name.getValue().equals(actionName)){
             if (null != clas) {
                 attrVal = clas.getValue();
             }
             for(Iterator itt = element.elementIterator();itt.hasNext();){
            	 Element elementt = (Element) itt.next(); // 获取标签对象
            	 Attribute viename = elementt.attribute("name");
            	 if(viename.getValue().equals("success")){
            		 jsp = elementt.getText();
            	 }else{
            		 jsp = elementt.getText();
            	 }
             }
             }
    	}

    	
    	//String actionU = (String) actionUrl.get(0);
    	try {
			Class<?> clz = Class.forName(attrVal);
			try {
				Object obj = clz.newInstance();
				Method setName = obj.getClass().getMethod("setName",String.class);
				Method setPassword = obj.getClass().getMethod("setPassword",String.class);
				setName.invoke(obj,parameters.get("name"));
				setPassword.invoke(obj,parameters.get("password"));
				Method m = clz.getDeclaredMethod("execute");
				m.invoke(obj);
				Method getMessage = obj.getClass().getMethod("getMessage");
				HashMap hm = new HashMap();
				hm.put("message", getMessage.invoke(obj));
				retvie = new View();
				retvie.setJsp(jsp);
				retvie.setParameters(hm);
//				view = Class.forName("View");
//				Object vieobj = view.newInstance();
//				Method  setParameters = vieobj.getClass().getMethod("setParameters", Map.class);
//				setParameters.invoke(vieobj, hm);
//				Method  setJsp = vieobj.getClass().getMethod("setJsp", String.class);
//				setJsp.invoke(vieobj, jsp);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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
    	
    	return retvie;
    } 

}
