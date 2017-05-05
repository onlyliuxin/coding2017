package com.company.coderising.litestruts;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;



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
        try {
            DocumentBuilderFactory dbfactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder dbuilder=dbfactory.newDocumentBuilder();
            Document doc=dbuilder.parse("src/com/company/litestruts/struts.xml");
            doc.getDocumentElement().normalize();

            NodeList list=doc.getElementsByTagName("action");
            for(int i=0;i<list.getLength();i++){
                Node node=list.item(i);
                Element element= (Element) node;
                if (element.getAttribute("name").equals(actionName)){
                    Class c=Class.forName(element.getAttribute("class"));
                    Object o=c.getInterfaces();
                    Method setName=c.getDeclaredMethod("setName",String.class);
                    setName.invoke(o,parameters.get("name"));
                    Method setPassword = c.getDeclaredMethod("setPassword", String.class);
                    setPassword.invoke(o, parameters.get("password"));
                    Method execute=c.getDeclaredMethod("execute",null);
                    String result=execute.invoke(o).toString();
                    Method getMessage=c.getDeclaredMethod("getMessage",null);
                    HashMap<String,String > map=new HashMap<String, String>();
                    map.put("message",getMessage.invoke(o).toString());
                    View view=new View();
                    view.setParameters(map);
                    NodeList nodeListT=element.getElementsByTagName("result");
                    for (int j=0;j<nodeListT.getLength();j++){
                        Node nodeT=list.item(j);
                        Element elementT= (Element) nodeT;
                        if (elementT.getAttribute("name").equals(result)){
                            view.setJsp(nodeT.getTextContent());
                        }
                    }
                    return view;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    	return null;
    }    

}
