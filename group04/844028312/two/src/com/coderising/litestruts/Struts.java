package com.coderising.litestruts;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Struts { 
	private  Map<String,String> action=new HashMap<String,String>();
	private  boolean find=false;
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
    	
    	View v=new View();
    	//创建SAXReader对象 
    	SAXReader reader = new SAXReader();   
    	Struts struts=new Struts();
    	File file=new File(Struts.class.getResource("struts.xml").getFile());
    	Document document;
		try {
			document = reader.read(file);
			Element root=document.getRootElement();
			struts.listNodes("login",root);
			if(struts.getAction().size()!=0){
				try {
					String className=struts.getAction().get("class");
					Class c=Class.forName(className);
					Object obj = c.newInstance();
					 Method[] methods = c.getDeclaredMethods();
					 Iterator iter = parameters.entrySet().iterator();			
						while (iter.hasNext()) {
							Map.Entry entry = (Map.Entry) iter.next();
							Object key = entry.getKey();
							Object val = entry.getValue();
						 for(Method m:methods){
							 String sName=m.getName().substring(0, 3);
							 String eName=m.getName().substring(3, m.getName().length()).toLowerCase();
							
							 if("set".equals(sName)&&key.equals(eName)){
								 m.invoke(obj, val);
								 break;
							 }
						 }
						}
					 Method exectue= c.getDeclaredMethod("execute"); 
					 String key=(String) exectue.invoke(obj, null);
					 String jsp;
					 if(key!=null&&!"".equals(key)){
						 jsp=struts.getAction().get(key);
						 v.setJsp(jsp);
					 }
					 Map map=new HashMap<>();
					 for(Method m:methods){
						 String sName=m.getName().substring(0, 3);
						 
						 if("get".equals(sName)){
							String key2=m.getName().substring(3, m.getName().length()).toLowerCase();
							String values=(String) m.invoke(obj, null);
							map.put(key2, values);
						 }
					 }	
					 v.setParameters(map);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return v;
    }   
    public Map<String, String> getAction() {
		return action;
	}
	public void setAction(Map<String, String> action) {
		this.action = action;
	}
	public  Map listNodes(String actionName,Element node){
    	String name=node.getName();			//节点名字
        if("action".equals(name)){
        		List<Attribute> attributes=node.attributes();//获取节点属性
             	for(Attribute attribute:attributes){
             		String aName=attribute.getName();
             		if("name".equals(aName)&&attribute.getValue().equals(actionName)){
             			for(Attribute attribute2:attributes){
             				String cName=attribute2.getName();
             				if("class".equals(cName)){
                 				action.put(cName, attribute2.getValue());
                 				Iterator<Element> iterator=node.elementIterator();
                 				while(iterator.hasNext()){
                 					Element rNode=iterator.next();
                 					String result=rNode.getName();
                 					if("result".equals(result)){
                 			    		List<Attribute> attributes3=rNode.attributes();
                 			    		for(Attribute attribute3:attributes3){
                 			        		String rName=attribute3.getName();
                 			        		if("name".equals(rName)){
                 			        			String rValue=attribute3.getValue();
                 			        			action.put(rValue, rNode.getTextTrim());
                 			        			break;
                 			        		}
                 			        	}
                 			    		
                 			    		
                 			    	}
                 					else{
                 						break;
                 					}
                 				}
                 				break;
                 				}
             			}
             			find=true;
             			break;
             		}
             	}
        	 }
        	Iterator<Element> iterator=node.elementIterator();
        	while(iterator.hasNext()&&!find){
        		listNodes(actionName,iterator.next());
        	}
        	return null;
    }
  

}
