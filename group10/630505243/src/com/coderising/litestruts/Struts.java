package com.coderising.litestruts;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {
    	View view = new View();
    	//读取配置文件
    	try {
    		File f = new File("src/com/coderising/litestruts/struts.xml");
    		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(f);

			NodeList actionList = doc.getElementsByTagName("action");
			for(int i=0;i<actionList.getLength();i++){
				Node actionNode = actionList.item(i);
				if(actionNode.getNodeType()==Node.ELEMENT_NODE	){
					Element actionElement = (Element)actionNode;
					String actionNm = actionElement.getAttribute("name");
					String actionClass = actionElement.getAttribute("class");
					if(actionNm.equals(actionName)){
						Class clazz = Class.forName(actionClass);
						Object nobj = clazz.newInstance();
						Set<Map.Entry<String, String>> set = parameters.entrySet();
						Map<String,String> viewparam = new HashMap<String,String>();
						for(Map.Entry<String, String> entry:set){
							String key = entry.getKey();
							String value = entry.getValue();
							Method method = clazz.getMethod("set"+key.substring(0,1).toUpperCase()+key.substring(1,key.length()), String.class);
							method.invoke(nobj, value);
							//viewparam.put(key, value);
						}
						Method m = clazz.getMethod("execute", null);
						String rtnStr = (String) m.invoke(nobj, null);
						NodeList resultList = actionElement.getChildNodes();
						for(int j=0;j<resultList.getLength();j++){
							Node resultNode = resultList.item(j);
							if(resultNode.getNodeType()==Node.ELEMENT_NODE){
								Element resultElement = (Element)resultNode;
								String resultNm = resultElement.getAttribute("name");
								String resultVal = resultElement.getTextContent();
								if(rtnStr.equals(resultNm)){
									
									view.setJsp(resultVal);
									Field[] fields = nobj.getClass().getDeclaredFields();
									
									for(Field fld:fields){
										fld.setAccessible(true);
										viewparam.put(fld.getName().toString(),fld.get(nobj).toString());
									}
									view.setParameters(viewparam);
								}
							}
						}
					}
				}
			}
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
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
    	
    	return view;
    }    

}
