package com.coderising.litestruts;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
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
    	View view=new View();
    	try {
    		SAXReader reader=new SAXReader();
			Document doc=reader.read("./src/com/coderising/litestruts/struts.xml");
			Element root=doc.getRootElement();
			List<Element> list=root.elements();//将所有元素放到集合中
			List<Map<String,String>> listMap=getElements(list);
			int size=listmap.size();
			listMap.remove(size-1);
			System.out.println(listMap.toString());
			
			String pathName="";
			int index=0;
			String name=parameters.get("name");
			String password=parameters.get("password");
			
			Map<String,Object> parameter=new HashMap<>();
			
			for(int i=0;i<size;i++){
				pathName=listMap.get(i).get(actionName);
				if(pathName!=null){
					index=i;
					break;
				}
				
			}
			Class clz=Class.forName(pathName);
			Object obj=clz.newInstance();
			Method setName=clz.getDeclaredMethod("setName", String.class);
			Method setPassword=clz.getDeclaredMethod("setPassword", String.class);
			Method execute=clz.getDeclaredMethod("execute");
			Method getMessage=clz.getDeclaredMethod("getMessage");
			setName.invoke(obj, name);
			setPassword.invoke(obj, password);
			
			Object result=execute.invoke(obj);
			Object message=getMessage.invoke(obj);
			
			parameter.put("message", message);
			view.setParameters(parameter);
			
			String jsp=listMap.get(index).get(result);
			view.setJsp(jsp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return view;
    }    

    
    private static List<Map<String,String>> listmap = new ArrayList<Map<String,String>>();
    private static Map<String,String> map=new LinkedHashMap<String,String>();
    private static List<Map<String,String>> getElements(List<Element> list){
    	
    	for(Iterator<Element> itera1=list.iterator();itera1.hasNext();){
    		String key="";
			String value="";
    		//获取action、result
    		Element element=itera1.next();
    		if(element.getName()=="action"){
    			map = new LinkedHashMap<String,String>();
    		}
    		if(element.getText().trim()!=null){
    			value=element.getText();
    		}
    		List<Attribute> attributes=element.attributes();
    		
    		
    		//获取action、result里的属性名和值
    		for(Iterator<Attribute> itera2=attributes.iterator();itera2.hasNext();){
    			Attribute attr=itera2.next();
    			
    			if(attr.getName().equals("name")){
    				key=attr.getValue();
    			}
    			if(attr.getName().equals("class")){
    				value=attr.getValue();
    				
    			}
    			if(!key.trim().isEmpty()&&!value.trim().isEmpty()){
    				map.put(key, value);
    			}
    		}
    		
    		List<Element> subList=element.elements();
    		//递归action的的子元素们 
    		if(subList.size()!=0){
    			getElements(subList);
    		}
    	}
    	listmap.add(map);
		return listmap;
    }
}
