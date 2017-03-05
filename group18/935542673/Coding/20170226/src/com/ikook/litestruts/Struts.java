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
package com.ikook.litestruts;

import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author ikook   QQ号码: 935542673
 */
public class Struts {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static View runAction(String actionName, Map<String, String> parameters) {

		SAXReader reader = new SAXReader();
		View view = new View();
		try {
			File f = new File("src/com/ikook/litestruts/struts.xml");
			Document e = reader.read(f);

			Element struts = e.getRootElement();
			Iterator it = struts.elementIterator();

			while (it.hasNext()) {
				Element action = (Element) it.next();
				
				List<Attribute> actionAtr = action.attributes();
				if (!actionAtr.get(0).getValue().equals(actionName))
					continue;
				
				String className = actionAtr.get(1).getValue();
				Class aClass = Class.forName(className);
				
				Object o = aClass.newInstance();
				Field field;
				for (Map.Entry<String, String> entry : parameters.entrySet()) {
					field = aClass.getDeclaredField(entry.getKey());
					field.setAccessible(true);
					field.set(o, entry.getValue());
				}

				Method m = aClass.getMethod("execute");
				String resultString = (String) m.invoke(o);
				Method m2 = aClass.getMethod("getMessage");
				String message = (String) m2.invoke(o);

				Map<String, String> map = new HashMap<>();

				map.put("message", message);
				view.setParameters(map);
				it = action.elementIterator();
				
				while (it.hasNext()) {
					Element result = (Element) it.next();
					String s = result.attribute(0).getValue();
					if (resultString.equals(s)) {
						view.setJsp(result.getStringValue());
						return view;
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}

}
