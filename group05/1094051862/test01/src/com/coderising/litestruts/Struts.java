package com.coderising.litestruts;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.xml.sax.SAXException;



public class Struts {
	@Test
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
    	//DOM4J方式解析xml
        SAXReader reader = new SAXReader();  
        //读取文件 转换成Document  
        try {
			Document document = reader.read(new File("struts.xml"));
			Element root = document.getRootElement();
			//根节点的子节点
			List<Element> nodes = root.attributes();
			Element element;
			for (int i = 0; i < nodes.size(); i++) {
				if (nodes.get(i).attribute("name").toString().equals(actionName)){
					Attribute c = nodes.get(i).attribute("class");
					c.toString();
				}
			}
			System.out.println();
		} catch (DocumentException e) {
			e.printStackTrace();
		}  
    	return null;
    }    

}
