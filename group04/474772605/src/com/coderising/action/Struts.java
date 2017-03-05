package com.coderising.action;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Struts {
	
	
	
	public static void main(String[] args) throws DocumentException {
String actionName = "com.coderising.action.LoginAction";
        
		Map<String,String> params = new HashMap<String,String>();
        params.put("name","test");
        params.put("password","1234");
        
        
        View view  = Struts.runAction(actionName,params);        
	}
	
 

/*  //遍历当前节点下的所有节点  
  public static void listNodes(Element node){  
      System.out.println("当前节点的名称：" + node.getName());  
      //首先获取当前节点的所有属性节点  
      List<Attribute> list = node.attributes();  
      //遍历属性节点  
      for(Attribute attribute : list){  
          System.out.println("属性"+attribute.getName() +":" + attribute.getValue());  
      }  
      //如果当前节点内容不为空，则输出  
      if(!(node.getTextTrim().equals(""))){  
           System.out.println( node.getName() + "：" + node.getText());    
      }  
      //同时迭代当前节点下面的所有子节点  
      //使用递归  
      Iterator<Element> iterator = node.elementIterator();  
      while(iterator.hasNext()){  
          Element e = iterator.next();  
          listNodes(e);  
      }  
  } */ 

    public static View runAction(String actionName, Map<String,String> parameters)  {
    	
    	 SAXReader reader = new SAXReader();  
	      //读取文件 转换成Document  
	      org.dom4j.Document document;
		try {
			document = reader.read(new File("src/struts.xml"));
			Element root = document.getRootElement();  		    
		      List<Element> childList2 = root.elements("action");
		      Iterator<Element> it = childList2.iterator();
		     /* while (it.hasNext()){
		      Element element = it.next();
		      System.out.println("节点的名称" + element.getName() + "节点的值" + element.getText()); 

		         }*/
		      for (int i = 0; i < childList2.size(); i++) {
		 	     List<Attribute> list = childList2.get(i).attributes();  
		 	     Iterator<Attribute> it1 = list.iterator();
		 	     while(it1.hasNext()){
		 	    	 
		 	    	Attribute attribute1 = it1.next();
		 	    	System.out.println(attribute1.getName());
		 	    	System.out.println(attribute1.getValue());
		 	    	System.out.println("==============");
		 	    
		 	     } 
		 	     
		 	    	 for(Attribute attribute : list){ 	
		 	    		
		 	    		 if(actionName.equals(attribute.getValue())){
		 	    			 
		 	    			 String clazz = null;
		 	    		
		 	    			 clazz = attribute.getValue();
		 	    			try {
								Object o = Class.forName(clazz).newInstance();
								try {
									Method m = o.getClass().getMethod("setName", String.class);
									Method m1 = o.getClass().getMethod("setPassword", String.class);
									Method m3 = o.getClass().getMethod("execute");
									try {
										m.invoke(o, parameters.get("name"));
										m1.invoke(o, parameters.get("password"));
										String result = (String) m3.invoke(o);
										System.out.println(result);
									} catch (IllegalArgumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} catch (SecurityException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (NoSuchMethodException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								
							} catch (InstantiationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}  //根据class生成实例
		 	    		 }
		 	    	
		 	    		 
		 	    	 }  	  
		 		     
		 		}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	      //获取根节点元素对象  
	      
	   
    	

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

}
