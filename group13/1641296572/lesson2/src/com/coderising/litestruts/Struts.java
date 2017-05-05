package com.coderising.litestruts;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

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
 * 
 */
public class Struts
{
	public static View runAction(String actionName, Map<String, String> parameters)

	{
		View view = new View();
		
		try
		{
			//0. 读取配置文件struts.xml
			Map<String, ActionObject> actionMap = getActionMapFromXml(new File("struts.xml"));
			//1.根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象）
			String className = actionMap.get(actionName).getActionClass();
			Class<?> clazz = Class.forName(className);
			Object action = clazz.newInstance();
			
			// 据parameters中的数据，调用对象的setter方法
			Set<Entry<String, String>> entrySet = parameters.entrySet();
			for (Entry<String, String> entry : entrySet)
			{
				String key = entry.getKey();
				String value = entry.getValue();
				Method method = null;
				method = clazz.getDeclaredMethod("set" + upperFirstChar(key), String.class);
				method.invoke(action, value);
			}
			
			//2.通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
			Method executeMethod = null;
			executeMethod = clazz.getDeclaredMethod("execute");
			String resultMsg = (String) executeMethod.invoke(action);

			//3.通过反射找到对象的所有getter方法,通过反射来调用， 把值和属性形成一个HashMap,放到View对象的parameters
			Map<String, String> paraMap = new HashMap<String, String>();
			// 用"get"+属性 来获取getter方法
			Field[] fields = clazz.getDeclaredFields();
			for (Field f : fields)
			{
				Method m = null;
				m = clazz.getDeclaredMethod("get" + upperFirstChar(f.getName()));
				String key = f.getName();
				String value = (String) m.invoke(action);;
				paraMap.put(key, value);
			}
			view.setParameters(paraMap);
			
			//4.根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp， 放到View对象的jsp字段中。
			String jsp = actionMap.get(actionName).getResultMap().get(resultMsg);
			view.setJsp(jsp);
			
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (InstantiationException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		} catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			e.printStackTrace();
		} catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		} catch (SecurityException e)
		{
			e.printStackTrace();
		}

		return view;
	}


	private static Map<String, ActionObject> getActionMapFromXml(File file)
	{
		SAXReader sr = new SAXReader();
		Document dc = null;
		try
		{
			dc = sr.read(file);
		} catch (DocumentException e)
		{
			e.printStackTrace();
			return null;
		}
		Map<String, ActionObject> actionMap = new HashMap<String, ActionObject>();
		Element root = dc.getRootElement();
		Iterator<Element> it = root.elementIterator();
		while (it.hasNext())
		{
			Element ele = it.next();
			ActionObject ao = new ActionObject();
			ao.setActionName(ele.attribute("name").getText());
			ao.setActionClass(ele.attribute("class").getText());
			List<Element> subEles = ele.elements();
			for (Element sub : subEles)
			{
				Map<String, String> map = ao.getResultMap();
				map.put(sub.attribute("name").getText(), sub.getTextTrim());

			}

			actionMap.put(ao.getActionName(), ao);
		}
		return actionMap;
	}

	private static String upperFirstChar(String str)
	{
		if (null == str || str.length() == 0)
		{
			return "";
		}

		char[] chars = str.toCharArray();
		if (chars[0] >= 'a' && chars[0] <= 'z')
		{
			chars[0] -= 32;
		}
		return new String(chars);
	}

}
