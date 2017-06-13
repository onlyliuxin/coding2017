package cn.task2;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
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
    	SAXReader read = new SAXReader();
    	Document document = null;
    	View view = new View();
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
			document = read.read(Struts.class.getClassLoader().getResourceAsStream("struts.xml"));
			//获取唯一的根节点
			Element root = document.getRootElement();
			//获取子节点的集合
			List<Element> childNode = root.elements();
			
			for(Element ele : childNode){
				//获取action节点的name和class值
				String name = ele.attributeValue("name");
				String className = ele.attributeValue("class");
				
				
				if(name.equals(actionName)){
					try {
						Class clazz = Class.forName(className);
						Object obj = clazz.newInstance();
						for(Map.Entry<String, String> param : parameters.entrySet()){
							String key = "set"+param.getKey().substring(0,1).toUpperCase()+param.getKey().substring(1);
							Method m =clazz.getMethod(key,String.class);
//							System.out.println(param.getKey()+"  "+param.getValue());
							m.invoke(obj, param.getValue());
						}
						
						Method execute = clazz.getMethod("execute");
						Type type = execute.getReturnType();
						//获取result节点的name和返回的jsp
						List<Element> list2 = ele.elements();
						for(Element e:list2){
//							System.out.println(e.attributeValue("name"));
//							System.out.println(e.getText());
//							System.out.println(execute.invoke(obj));
							if(e.attributeValue("name").equals(execute.invoke(obj))){
								System.out.println(e.getText());
								view.setJsp(e.getText());
							}
						}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	view.setParameters(parameters);
    
    	return view;
    }    
}
