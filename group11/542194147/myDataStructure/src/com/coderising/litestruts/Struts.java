package com.coderising.litestruts;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class Struts {

	@SuppressWarnings({"unchecked" })
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
		View view=new View();
    	try {
    		SAXReader sr=new SAXReader();
			Document document= sr.read(new File("src/com/coderising/litestruts/struts.xml"));
			Element root=document.getRootElement();
			List<Element> actions=root.elements("action");
			for(Iterator<Element> it=actions.iterator();it.hasNext();){
				Element action =it.next();
				if(action.attribute("name").getText().equals(actionName)){
					LoginAction loginAction=(LoginAction)Class.forName(
							action.attribute("class").getText()).newInstance();
					loginAction.setName(parameters.get("name"));
					loginAction.setPassword(parameters.get("password"));
					String loginMsg=loginAction.execute();
					if(loginMsg.equals("success")){ 
						List<Element>results=action.elements("result");
						for(it=results.iterator();it.hasNext();){
							Element result=it.next();
							if(result.attribute("name").getText().equals("success")){
								createView(view, loginAction, result);
							}
						}
					}
					if(loginMsg.equals("fail")){
						List<Element>results=action.elements("result");
						for(it=results.iterator();it.hasNext();){
							Element result=it.next();
							if(result.attribute("name").getText().equals("fail")){
								createView(view, loginAction, result);
							}
						}
					}
					if(loginMsg.equals("error")){
						List<Element>results=action.elements("result");
						for(it=results.iterator();it.hasNext();){
							Element result=it.next();
							if(result.attribute("name").getText().equals("error")){
								createView(view, loginAction, result);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("have exception:"+e);
			e.printStackTrace();
		}
    	return view;
    }

	private static void createView(View view, LoginAction loginAction, Element result) {
		Map<String,String> msgMap=new HashMap<String,String>();
		msgMap.put("message", loginAction.getMessage());
		view.setParameters(msgMap);
		view.setJsp(result.getText());
	}    

}
