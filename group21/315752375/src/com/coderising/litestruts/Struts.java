package com.coderising.litestruts;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {
	public static View runAction(String actionName,
			Map<String, String> parameters) {
		View view = new View();
		try {
			InputStream inputStream = null;
			inputStream = new FileInputStream(new File(
					"./src/com/coderising/litestruts/struts.xml"));
			SAXReader saxReader = new SAXReader();
			Document document = null;
			document = saxReader.read(inputStream);
			Element root = document.getRootElement();//根节点struts
			Iterator<Element> actions = root.elements("action").iterator();//变量用于存储所有action的节点
			Element element = null;
			while (actions.hasNext()) {//获得name为参数actionName的action节点
				element = actions.next();
				if (actionName.equals(element.attributeValue("name"))) {
					break;
				}

			}
			Map<String, String> result = new HashMap<>();//变量用于存储action下result的name和value；
			Iterator<Element> results = element.elements("result").iterator();//获得name为参数actionName的action节点的所有result节点的迭代器
			while (results.hasNext()) {//读取所有的action下result的name和value；
				Element tmp = results.next();
				String resultname = tmp.attributeValue("name");
				String resultvalue = tmp.getText();
				result.put(resultname, resultvalue);
			}
			inputStream.close();
			String clazz = element.attributeValue("class");//获得actionName对应action的class属性对应的值
//			System.out.println(clazz);
			Object object = null;
			object = Class.forName(clazz).newInstance();//
			int len = parameters.size();
			Method method =null;
			Iterator<Map.Entry<String, String>> map = parameters.entrySet()
					.iterator();
			while (map.hasNext()) {//取得所有parameters中的map，调用上面类实例中map中key值的setter，设为value；
				Map.Entry<String, String> tmp = map.next();
				String namemethod = "set"
						+ tmp.getKey().substring(0, 1).toUpperCase()
						+ tmp.getKey().substring(1);
				method = object.getClass().getMethod(namemethod,
						String.class);
				String namevalue = tmp.getValue();
				method.invoke(object, namevalue);
			}
			Method execute = null;
			String loginresult = null;//登陆结果"success",or"fail"
			execute = object.getClass().getMethod("execute");
			
			loginresult = (String) execute.invoke(object);
			view.setJsp(result.get(loginresult));
			int count=object.getClass().getDeclaredMethods().length;
			Map<String, String> viewParameters=new HashMap<>();
			while(count--!=0){//获取所有的getter
				method=object.getClass().getDeclaredMethods()[count];
				String mName=method.getName();
				if(mName.startsWith("get")){
					String value=(String)method.invoke(object);
					String key=(String)mName.substring(3,4).toLowerCase()+mName.substring(4);
					viewParameters.put(key, value);
				}
			}
			view.setParameters(viewParameters);
//			System.out.println(view.getJsp());
//			System.out.println(view.getParameters().get("message"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * 
		 * 0. 读取配置文件struts.xml
		 * 
		 * 1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象）
		 * 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 ("name"="test" ,
		 * "password"="1234") , 那就应该调用 setName和setPassword方法
		 * 
		 * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		 * 
		 * 3. 通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用， 把值和属性形成一个HashMap , 例如
		 * {"message": "登录成功"} , 放到View对象的parameters
		 * 
		 * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp，
		 * 放到View对象的jsp字段中。
		 */

		return view;
	}
}
