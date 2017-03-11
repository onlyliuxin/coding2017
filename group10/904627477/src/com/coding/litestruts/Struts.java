package com.coding.litestruts;

import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;




public class Struts {

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
    public static View runAction(String actionName, Map<String,String> parameters) {
    	if(actionName==null){
    		return null;
    	}
    	View view = new View();
    	String path = getStrutsXMLPath();
    	Element actionEle = getActionElement(path, actionName);
    	if(actionEle==null){
    		return null;
    	}
    	String className = actionEle.attributeValue("class");
		Object action = ReflectUtil.getObject(className, parameters);
		if(action==null){
			return null;
		}
		String methodName = actionEle.attributeValue("method");
		methodName = methodName==null?"execute":methodName;
		Object reslut = ReflectUtil.exectue(action, methodName);
		String jsp = getElementJsp(actionEle, reslut!=null?reslut.toString():null);
		view.setJsp(jsp);
		view.setParameters(ReflectUtil.getAttributes(action));
		return view;
    }
    
    private static String getStrutsXMLPath(){
    	String path = Struts.class.getResource("").getPath()+"struts.xml";
    	path = path.substring(1);
    	return path;
    }
    
    @SuppressWarnings("unchecked")
	public static Element getActionElement(String path,String actionName){
    	if(path==null||actionName==null){
    		return null;
    	}
    	Element actionEle = null;
    	try {
			SAXReader read = new SAXReader();
			Document doc = read.read(path);
			Element root = doc.getRootElement();
			List<Element> actions = root.elements("action");
			for (Element element : actions) {
				String name = element.attributeValue("name");
				if(actionName.equals(name)){
					actionEle = element;
					break;
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
    	return actionEle;
    }
    
	@SuppressWarnings("unchecked")
	public static String getElementJsp(Element actionEle, String reslut) {
		String jsp = null;
		if(reslut!=null){
			List<Element> results = actionEle.elements("result");
			for (Element reslutEle : results) {
				String resName = reslutEle.attributeValue("name");
				resName = resName==null?"success":resName;
				if(reslut.equals(resName)){
					jsp = reslutEle.getText().trim();
				}
			}
		}
		return jsp;
	}

}
