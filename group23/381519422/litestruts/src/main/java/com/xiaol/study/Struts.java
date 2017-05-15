package com.xiaol.study;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.management.RuntimeErrorException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class Struts {
	
	// 每一个action的信息存放到一个Map<String, String>里
	// 所有的Map<String, String>放到rootMap方便统一管理
	private static HashMap<String, Map<String, String>> rootMap = new HashMap<String, Map<String, String>>();
	
    public static View runAction(String actionName, Map<String,String> parameters) {
    	View view = new View(); 
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
    	
		try {
			// 1、初始化读取配置文件
			init();

			// 2、获取根据参数获取对应的class的名字
			Map<String, String> actionMap = rootMap.get(actionName);
			String className = actionMap.get("class");
			// 3、根据名字获取Class对象
			Class<?> clazzType = Class.forName(className);
			// 4、通过无参构造获取一个对象
			Object newInstance = clazzType.getConstructor(new Class[] {}).newInstance(new Object[] {});
			// 5、调用set方法，把("name"="test" , "password"="1234")值set进去
			Set<Entry<String, String>> entrySet = parameters.entrySet();
			Iterator<Entry<String, String>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> next = iterator.next();
				String key = next.getKey();
				String value = next.getValue();
				String setMethodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
				Field declaredField = clazzType.getDeclaredField(key);
				Method setMehtod = clazzType.getDeclaredMethod(setMethodName, new Class[] { declaredField.getType() });
				setMehtod.invoke(newInstance, value);
			}
			// 6、执行execute方法
			String executeMethodName = "execute";
			Method executeMethod = clazzType.getDeclaredMethod(executeMethodName);
			Object resultString = (String) executeMethod.invoke(newInstance);

			// 7、通过反射获取message信息
			String getMethodName = "getMessage";
			Field declaredField = clazzType.getDeclaredField("message");
			Method getMehtod = clazzType.getDeclaredMethod(getMethodName);
			String message = (String) getMehtod.invoke(newInstance);

			// 8\
			String jsp = actionMap.get(resultString);
			view.setJsp(jsp);
			Map<String, String> hashMap = new HashMap<>();
			hashMap.put("message", message);
			view.setParameters(hashMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError();
		}
		return view;
    }    
    
	public static void init() throws Exception {

		// 1、获取工厂实例
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// 2、获取dom解析器
		DocumentBuilder db = dbf.newDocumentBuilder();
		// 3、解析xml文档，获取document对象(根节点)
		// 也可以获取流使用db.parse(InputStream)
		// InputStream is =
		// this.getClass().getResourceAsStream("D:\\GitHub\\coding2017\\group23\\381519422\\litestruts\\src\\main\\resources\\struts.xml");
		Document document = db.parse(
				new File("D:\\GitHub\\coding2017\\group23\\381519422\\litestruts\\src\\main\\resources\\struts.xml"));
		NodeList list = document.getElementsByTagName("action");

		/*
		 * 开始循环list，list中每一个元素结构如下
		 * 	<action name="login" class="com.coderising.litestruts.LoginAction">
				<result name="success">/jsp/homepage.jsp</result>
				<result name="fail">/jsp/showLogin.jsp</result>
			</action>
		 */
		for (int i = 0; i < list.getLength(); i++) {
			Map<String, String> actionMap = new HashMap<String, String>();
			// 注意：使用的都是org.w3c.dom包下的
			Element element = (Element) list.item(i);
			// 获取<action name="login">中 name对应的属性值
			String actionAttributeName = element.getAttribute("name");
			String className = element.getAttribute("class");
			actionMap.put("class", className);

			// 读取第一个action
			NamedNodeMap nnm = element.getElementsByTagName("result").item(0).getAttributes();
			// 获取<result name="success">的属性 name
			// String nodeName = nnm.item(0).getNodeName();
			// element.getElementsByTagName("result").item(0)
			// 是<result name="success">/jsp/homepage.jsp</result>
			// getFirstChild().getNodeValue() 是获取上面内容的/jsp/homepage.jsp
			String context = element.getElementsByTagName("result").item(0).getTextContent();
			// 获取<result name="success">的属性 name对应的值success
			String nodeValue = nnm.item(0).getNodeValue();
			actionMap.put(nodeValue, context);
			
			// 读取第二个action
			nnm = element.getElementsByTagName("result").item(1).getAttributes();
			// 获取<result name="success">的属性 name
			// nodeName = nnm.item(0).getNodeName();
			context = element.getElementsByTagName("result").item(1).getFirstChild().getNodeValue();
			// 获取<result name="success">的属性 name对应的值success
			nodeValue = nnm.item(0).getNodeValue();
			actionMap.put(nodeValue, context);
			
			rootMap.put(actionAttributeName, actionMap);
		}
	}
	
	public static void main(String[] args) {
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 内部类用于存放从配置文件读取的数据
	class StrutsMap {
		private HashMap<String, String> map;

		public String get(String key) {
			return map.get(key);
		}

		public void set(String key, String value) {
			map.put(key, value);
		}
	}
}
