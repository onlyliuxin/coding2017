package com.coderising.litestruts;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {

	@SuppressWarnings("unchecked")
	public static View runAction(String actionName, Map<String, String> parameters) throws Exception {
		// 0. 读取配置文件struts.xml
		// 1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象）
		// 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
		// ("name"="test" , "password"="1234") ,
		// 那就应该调用 setName和setPassword方法
		//
		// 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		//
		// 3. 通过反射找到对象的所有getter方法（例如 getMessage）,
		// 通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message": "登录成功"} ,
		// 放到View对象的parameters
		//
		// 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp，
		// 放到View对象的jsp字段中。
		View view = new View();
		// 0. 读取配置文件struts.xml
		SAXReader reader = new SAXReader();
		// 读取文件 转换成Document
		Document document = reader.read(new File("src/com/coderising/litestruts/struts.xml"));
		// 获取根节点元素对象
		Element root = document.getRootElement();
		//根节点的元素
		Iterator<Element> it = root.elementIterator();
		while(it.hasNext()){
			Element e = (Element) it.next();
			if(actionName.equals(e.attributeValue("name"))){
				// 1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象）
				String className = e.attributeValue("class");
				Class clazz = Class.forName(className);
				Object obj = clazz.newInstance(); 
				//获取类的所有属性
				BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
				PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
				for(PropertyDescriptor pd : pds){
					for (Entry<String, String> entry : parameters.entrySet()) {
						String key = entry.getKey();
						if(pd.getName().equals(key)){
							Method setMethod = pd.getWriteMethod();//获得set方法
							setMethod.invoke(obj, entry.getValue());//调用
							break;
						}
					}
				}
		        //2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		        Method method = clazz.getMethod("execute", null);
		        Object result = method.invoke(obj);
		        Map<String, String> map = new HashMap<String, String>();
		        // 3. 通过反射找到对象的所有getter方法（例如 getMessage）,
				// 通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message": "登录成功"} ,
				// 放到View对象的parameters
		        for(PropertyDescriptor pd : pds){
					Method getMethod = pd.getReadMethod();//获得get方法
					Object getresult = getMethod.invoke(obj);//调用
					if(!"class".equals(pd.getName())){
						map.put(pd.getName(), getresult.toString());
					}
				}
		        view.setParameters(map);
		        //遍历action的子元素
		        Iterator<Element> it2 = e.elementIterator();
		        while(it2.hasNext()){
					Element resultEle = (Element) it2.next();
					if(resultEle.attributeValue("name").equals(result)){
						view.setJsp(resultEle.getText());
						break;
					}
		        }	
			}
		}
		return view;
	}
	
	public static void main(String[] args) throws Exception {
		String actionName = "login";
		Map<String,String> params = new HashMap<String,String>();
        params.put("name","test");
        params.put("password","1234"); 
		View view = runAction(actionName, params);
		System.out.println("view jsp:"+view.getJsp());
		System.out.println("view param:"+view.getParameters());
	}

}
