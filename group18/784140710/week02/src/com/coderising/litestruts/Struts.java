package com.coderising.litestruts;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;



public class Struts {
	@Test
	public void test01(){
		Map<String,String> params = new HashMap<String,String>();
        params.put("name","test");
        params.put("password","1234");
		runAction("login",params);
	}

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
    	View view = new View();
    	Map<String,String> param = new HashMap<String,String>();
    	try {
			Document dom = new SAXReader().read(new FileInputStream("src/com/coderising/litestruts/struts.xml"));
			Element root = dom.getRootElement();
			Element action = (Element) root.selectSingleNode("/struts/action[@name='"+actionName+"']");
			
			String clz = action.attributeValue("class");
			Class<?> className = Class.forName(clz);
			Object act = className.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(className);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				String name = pd.getName();
				Method writeMethod = pd.getWriteMethod();
				Method readMethod = pd.getReadMethod();
				try {
					writeMethod.invoke(act, parameters.get(name));
					
				} catch (Exception e) {
					continue;
				}
			}
			
			Method method = className.getMethod("execute");
			String result = (String)method.invoke(act);
			
			for (PropertyDescriptor pd : pds) {
				String name = pd.getName();
				Method readMethod = pd.getReadMethod();
				try {
					param.put(name, (String) readMethod.invoke(act));
				} catch (Exception e) {
					continue;
				}
			}
			view.setParameters(param);
			
			String jsp = action.selectSingleNode("result[@name='"+result+"']").getText();
			view.setJsp(jsp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
    	return view;
    }    

}

















