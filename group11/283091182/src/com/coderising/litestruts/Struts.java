package com.coderising.litestruts;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Struts {

	public static View runAction(String actionName, Map<String, String> parameters) {

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
		
		//0. Load Structs.xml and return a map
		Map<String,Action> actionMap =  loadActionMap();
		
		//1.Find and initialize action instance to actionName provided
		Action action = actionMap.get(actionName);
		Object instance  = initializeActionInstance(action.getActionClass(),parameters);
		
		//2.invoke the execute method and get the result
		String result = executeAction(instance);
		
		//3.Extract info for view
		View view = new View();
		view.setParameters(extractInfo(instance));
		
		//4.set dispatcher jsp according to execution result
		view.setJsp(action.getActionResultJsp(result));
		
		return view;
	}

	private static Map<String, Action> loadActionMap() {
		try {
			
			InputStream is = Struts.class.getResourceAsStream("struts.xml");
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(is);
			Element struct = document.getDocumentElement();
			
			return getActions(struct);
			
		} catch (IOException e) {
			throw new RuntimeException("Error Reading Configuration XML",e);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException("Error Parsing Configuration XML",e);
		} catch (SAXException e) {
			throw new RuntimeException("Error Parsing Configuration XML",e);
		}
	}

	/**
	 * Parse the XML and construct ActionName:Action map
	 * 
	 * @param Element struct, root of the struct xml
	 * @return Map<String,Action>
	 */
	private static Map<String,Action> getActions(Element struct) {
		
		Map<String,Action> map = new HashMap();
		
		NodeList nl = struct.getElementsByTagName(Action.CONST_ACTION);

		for (int i = 0; i < nl.getLength(); i++) 
		{
			Node actionNode = nl.item(i);
			// Get action name and corresponding action class from property
			String actionClass = getAttribute(actionNode,Action.CONST_CLASS);
			String actionName = getAttribute(actionNode,Action.CONST_NAME);

			Action action = new Action(actionName, actionClass);

			// get results under action
			NodeList childNodes = actionNode.getChildNodes();
			
			for (int j = 0; j < childNodes.getLength(); j++) 
			{
				Node result = childNodes.item(j);
				//Only accept if Node Type is element and Node name is "result"
				if ((result.getNodeType() == result.ELEMENT_NODE) 
						&& (result.getNodeName() == Action.CONST_RESULT)) 
				{
					String resultName = getAttribute(result,Action.CONST_NAME);
					String dispatcherJsp = result.getTextContent();
					action.setActionResultJsp(resultName, dispatcherJsp);
				}
			}
			map.put(action.getActionName(), action);
		}
		System.out.println(map);
		return map;
	}
	/**
	 * Get property from given node
	 * @param node
	 * @param key
	 * @return attribute as String
	 */
	private static String getAttribute(Node node,String key){
		NamedNodeMap map = node.getAttributes();
		Node attriNode =  map.getNamedItem(key);
		if(attriNode!=null && attriNode.getNodeType()==Node.ATTRIBUTE_NODE){
			return attriNode.getNodeValue();
		}
		return null;
	}
	
	/**
	 * Initialize instance from given class name and parameters map
	 * @param actionClass
	 * @param parameters
	 * @return instance of specified class
	 */
	private static Object initializeActionInstance(String actionClass,Map parameters){
		try {
			Class clazz= Class.forName(actionClass);
			//Instantiate by calling constructor
			Constructor constructor = clazz.getConstructor();
			
			constructor.setAccessible(true);
			Object instance = constructor.newInstance(new Object[]{});
			
			//Check class propertes with instrospector
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
			
			for(PropertyDescriptor prop:props){
				String propName = prop.getName();
				Method propSetter = prop.getWriteMethod();
				//If there is a setter for the property and also there is a value in parameter map
				//then invoke the setter method to set the values
				if(propSetter!=null && parameters.containsKey(propName))
				{
					propSetter.invoke(instance, parameters.get(propName));
				}
			}
			
			return instance;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error initializing instance: ClassName="+actionClass,e);
		}
	}
	
	
	/**
	 * Invoke the "execute" method from the action instance
	 * @param Action instance
	 * @return execute result as String
	 */
	private static String executeAction(Object instance){
		Class clazz = instance.getClass();
		try {
			//exepct no argument for execute method
			Method execute = clazz.getMethod("execute", new Class[0]);
			return (String)execute.invoke(instance, new Object[0]);
		} catch (Exception e) {
			throw new RuntimeException("Error executing action,class name="+clazz.getCanonicalName());
		}
	}
	
	
	/**
	 * Extracting Bean info by calling the getting method in the Action instance
	 * @param instance
	 * @return map
	 */
	private static Map extractInfo(Object instance){
		Map<String,Object> map = new HashMap<String,Object>();
		Class clazz = instance.getClass();
		try{
			Method[] methods = clazz.getMethods();
			for(Method method:methods)
			{
				String methodName = method.getName();
				if(methodName.startsWith("get")&&method.getParameterTypes().length==0)
				{
					Object methodReturn = method.invoke(instance, new Object[0]);
					//construct the properties name by getter method name,first character toLower case
					String propName = methodName.replaceFirst("get", "");
					char[] propNameCharArr = propName.toCharArray();
					propNameCharArr[0]=Character.toLowerCase(propNameCharArr[0]);
					
					map.put(String.valueOf(propNameCharArr), methodReturn);
				}
			}
			
		}catch(Exception e){
			throw new RuntimeException("Error extracting info from Action Insance,class="+clazz.getCanonicalName(),e);
		}
		return map;
	}

}
