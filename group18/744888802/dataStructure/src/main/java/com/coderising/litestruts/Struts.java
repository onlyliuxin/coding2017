package com.coderising.litestruts;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.Map;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {

        //读取xml
            DocumentBuilderFactory documentBuilderFactory =  DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
            Document doc = db.parse("src\\main\\resources\\struts.xml");
            NodeList nodeList = doc.getElementsByTagName("action");

            Map<String,Object> actionMap = new HashMap<String,Object>();
            for(int i=0;i<nodeList.getLength();i++)
            {
                Node node = nodeList.item(i);
                Element action = (Element)node;
                String xmlActionName = action.getAttribute("name");
                String xmlActionClassName = action.getAttribute("class");
                if(actionName.equals(xmlActionName)){

                }
                Class<?> actionClass = Class.forName(xmlActionClassName);
                actionMap.put(xmlActionName,actionClass.newInstance());
            }


            actionMap.get(actionName);




        } catch (Exception e) {
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
    	
    	return null;
    }

    public static void main(String[] args) {

        System.out.println(System.getProperty("user.dir") );
        runAction(null,null);
    }

}
