package com.coding.litestruts;

import java.util.Map;




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
    	Action actionEle = StrutsXMLParser.getStrutsXML(path).get(actionName);
    	if(actionEle==null){
    		return null;
    	}
    	Object action = ReflectUtil.getObject(actionEle.getClazz(), parameters);
    	if(action==null){
    		return null;
    	}
    	Object reslut = ReflectUtil.exectue(action, actionEle.getMethod());
    	if(reslut==null){
    		return null;
    	}
    	Result resultEle = actionEle.getResults().get(reslut.toString());
    	view.setJsp(resultEle.getJspPath());
    	view.setParameters(ReflectUtil.getAttributes(action));
		return view;
    }
    
    private static String getStrutsXMLPath(){
    	String path = Struts.class.getResource("").getPath()+"struts.xml";
    	path = path.substring(1);
    	return path;
    }
   

}
