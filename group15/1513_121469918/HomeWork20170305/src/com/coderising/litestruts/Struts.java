package com.coderising.litestruts;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Struts {

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
    	
    	
    
    	
    	//读取配置文件    	
    	try {		
    		Document doc =new SAXReader().read(new File("./src/com/coderising/litestruts/struts.xml"));
    		//获取XML中的action标签
    		Element loginName = (Element)doc.selectSingleNode("//action[1]");
    		Element logoutName = (Element)doc.selectSingleNode("//action[2]");
    		//判断action的name属性内容
    		if(actionName.equals(loginName.attributeValue("name"))){
    			//传入的actionName内容为login则进行login操作
    			
    			//获取class路径
    			String s = loginName.attributeValue("class");
    			Class c = Class.forName(s);
    			Constructor con = c.getConstructor();
    			//实例化LoginAction
    			LoginAction login =(LoginAction) con.newInstance();
    			
    			//通过parameters获取name，password
    			String executeName = parameters.get("name");
    			String executePassword = parameters.get("password");
    			
    			//获取 setter 方法,并调用
    			Method m = c.getMethod("setName", String.class);
    			m.invoke(login, executeName);
    			m =c.getMethod("setPassword", String.class);
    			m.invoke(login, executePassword);
    			
    			//调用LoginAction 的exectue 方法    exectueResult值为：帐号密码正确返回success，反之为fail 
    			m = c.getMethod("execute");
    			String exectueResult = (String)m.invoke(login);   
    			
    			//获取 getMessage 方法
    			m = c.getMethod("getMessage");
    			String message =(String) m.invoke(login);
    			m = c.getMethod("getName");
    			String name =(String) m.invoke(login);
    			m = c.getMethod("getPassword");
    			String password =(String) m.invoke(login);
    			
    			//创建HashMap,将登录操作返回的3个变量放到Map
    			HashMap<String,String> hm = new HashMap<String,String>();  			    			
    			hm.put("message", message);
    			hm.put("name", name);
    			hm.put("password", password);
    			
    			//创建View对象
    			View view = new View();
    			view.setParameters(hm);
    			
    			//读取struts.xml中的 <result> 配置
    			List<Element> list =(List<Element>) doc.selectNodes("//action[@name = 'login']/result");
    			for(Element e : list){
    				String resultName = e.attributeValue("name");
    				//exectue返回值比对XML下result 对应的值返回对应的jsp
    				if(resultName.equals(exectueResult)){
    					//将对应的jsp 放到view对象中
    					view.setJsp(e.getText()); 
    				}
    			}
    			return view;
    			
    		}else if(actionName.equals(logoutName.attributeValue("name"))){
    			//actionName是logout则进行logout操作
    		}
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return null;
    }    

}
