package com.coderising.litestruts;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.io.File;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.runners.Parameterized.Parameter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {

    	try{
    		File f=new File("src\\com\\coderising\\litestruts\\struts.xml");//找到xml文件
        	DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();//得到dom解析器工厂实例
        	DocumentBuilder builder =factory.newDocumentBuilder();//从工厂中得到DOM解析器
        	Document doc=builder.parse(f);//解析文件得到docunment
        	Element root=doc.getDocumentElement();//得到xml文档的根节点
        	NodeList books=root.getChildNodes();//得到每一个节点
        	if(books!=null){//如果不为空节点
        		for(int i=0;i<books.getLength();i++){
        			Node book=books.item(i);//获取此节点
        			String q=book.getNodeValue();
        			if(book.getNodeType()==Node.ELEMENT_NODE){//如果节点的属性值为<??> 则
        				String name=book.getAttributes().getNamedItem("name").getNodeValue();//获取<??>里的 属性值
        				String classname=book.getAttributes().getNamedItem("class").getNodeValue();
        				if(actionName.equals(name)){//如果传进来的值等于 属性name的值
        					Class<?> localclass=Class.forName(classname);//根据class属性的值创建class
        					Object object =localclass.newInstance();//创建class实例
        					for(Entry<String,String> entry :parameters.entrySet()){//循环遍历传进来的参数
        						String methodname=new StringBuffer("set").append(entry.getKey().substring(0,1).toUpperCase()).append(entry.getKey().substring(1)).toString();//根据参数来生成相应的set函数
        						Method method=localclass.getMethod(methodname,entry.getKey().getClass());
        						method.invoke(object, entry.getValue());//执行函数
        					}
        					Method methods=localclass.getMethod("execute");
        					String returnvalue=(String)methods.invoke(object);
        					Map<String,String> map=new HashMap<String,String>();
        					String namepara=(String) getter(object,"name");
        					String passwordpara=(String)getter(object,"password");
        					String message=(String)getter(object,"message");
        					map.put("name",namepara);
        					map.put("password", passwordpara);
        					map.put("message",message);
        					View view= new View();
        					view.setParameters(map);
        					String jsp = null;
        					if(returnvalue.equals(" ")){
        						
        					}
        					NodeList nl = doc.getElementsByTagName("result");
        					for(int w=0;w<nl.getLength();w++){
        						if(returnvalue.equals(doc.getElementsByTagName("result").item(w).getAttributes().getNamedItem("name").getNodeValue()))
        						{
        							jsp=doc.getElementsByTagName("result").item(w).getFirstChild().getNodeValue();
        							}
        						}
        					System.out.println(jsp);
        					view.setJsp(jsp);
        					return view;
        				}
        			}
        		}
        	}
    	}catch(Exception e){
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
    public static Object getter(Object object,String toGet){
    	Method target=null;
    	Object result=null;
    	try{
    		target=object.getClass().getMethod("get"+upperFirst(toGet), null);
    		result=target.invoke(object);
    		return result;
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return result;
    }
    public static String upperFirst(String toUpper){
    	return toUpper.substring(0,1).toUpperCase()+toUpper.substring(1);
    }
}
