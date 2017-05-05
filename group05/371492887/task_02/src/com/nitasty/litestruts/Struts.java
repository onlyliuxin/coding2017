package com.nitasty.litestruts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {

	public static View runAction(String actionName,
			Map<String, String> parameters) {
		
		View view=new View();

		/*
		 * 
		 * 0. 读取配置文件struts.xml
		 * 
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
		// 0.读取配置文件struts.xml
		try {
			// 将struts.xml转换成输入流
			InputStream in = new FileInputStream(new File(
					"src/com/nitasty/litestruts/struts.xml"));
			// 穿件SAXReader读取器，用于读取xml
			SAXReader saxReader = new SAXReader();
			//
			Document document = saxReader.read(in);
			// 获取根节点对象
			Element rootElement = document.getRootElement();
			//获取action节点列表
			List<Element> elementList=rootElement.elements();
			//加载第一个action的class类
			Element login=elementList.get(0);
			Class clazz=Class.forName(login.attribute("class").getStringValue());
			//new一个该class实例
			Object obj=clazz.newInstance();
			//获取name和password
			String name=parameters.get("name");
			String password=parameters.get("password");
			//获取setName方法
			Method setName=clazz.getMethod("setName",String.class);
			//获取setPassword方法
			Method setPassword=clazz.getMethod("setPassword",String.class);
			//获取execute方法
			Method execute=clazz.getMethod("execute");

			//执行获取的方法
			setName.invoke(obj,name);
			setPassword.invoke(obj,password);
			String result=(String) execute.invoke(obj);
			List<Element> results=login.elements();
			for (int i = 0; i < results.size(); i++) {
				if(result.equalsIgnoreCase(results.get(i).attribute(0).getStringValue())){
					view.setJsp(results.get(i).getTextTrim());
				}
			}
			
			//获取message属性
			Field fld=clazz.getDeclaredField("message");
			//允许访问私有属性
			fld.setAccessible(true);
			//获取该属性值
			String message=(String) fld.get(obj);
			
			//将结果返回
			Map<String,String> map=new HashMap<String,String>();
			map.put("message", message);
			view.setParameters(map);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		return view;
	}
	
	
	/**
	 * 测试用
	 * @param args
	 */
	public static void main(String[] args) {
		// 0.读取配置文件struts.xml
		try {
			// 将struts.xml转换成输入流
			InputStream in = new FileInputStream(new File(
					"src/com/nitasty/litestruts/struts.xml"));
			// 创建SAXReader读取器，用于读取xml
			SAXReader saxReader = new SAXReader();
			//
			Document document = saxReader.read(in);
			// 获取根节点对象
			Element rootElement = document.getRootElement();
			List<Element> elementList=rootElement.elements();
			
			System.out.println(elementList.get(0).attribute("name").getStringValue());
			System.out.println(elementList.get(0).attribute("class").getStringValue());

			
			Class clazz=Class.forName(elementList.get(0).attribute("class").getStringValue());
			
			Object obj=clazz.newInstance();
			
			Method setName=clazz.getMethod("setName",String.class);
			Method setPassword=clazz.getMethod("setPassword",String.class);
			setName.invoke(obj,"test");
			setPassword.invoke(obj,"1234");
			Method execute=clazz.getMethod("execute");
			String str=(String) execute.invoke(obj);
			Field fld=clazz.getDeclaredField("message");
			fld.setAccessible(true);
			String message=(String) fld.get(obj);
			
			System.out.println(str);
			System.out.println(message);
			elementList.get(0).attribute("name").getStringValue();
			elementList.get(0).attribute("class").getStringValue();

			Map map = new HashMap();
//			map = getAttributes(rootElement, map);

			// Element login=element.element("result");
			// System.out.println(login.getText());

			// for (Iterator it=rootElement.elementIterator(); it.hasNext();) {
			// System.out.println(it.next());
			// }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
