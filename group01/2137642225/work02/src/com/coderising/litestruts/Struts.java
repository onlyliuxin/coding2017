package com.coderising.litestruts;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


@SuppressWarnings("unchecked")
public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {
    	
    	if(actionName == null || actionName.trim().equals("")){
    		throw new RuntimeException("传入的actionName不能为null或者空");
    	}
    	
    	// 0. 读取配置文件struts.xml ok
    	URL resource = Struts.class.getResource("/com/coderising/litestruts");
    	String path = "";
    	try {
    		path = URLDecoder.decode(resource.getPath(), "UTF-8");
    	} catch (UnsupportedEncodingException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	Map<String,Map<String, Object>> actionMap = xmlParse(path + File.separator + "struts.xml");
    	
    	// 找到访问的action通过actionName
    	Map<String,Object> action = findAction(actionName,actionMap);
    	
    	//1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
    	//据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
    	//("name"="test" ,  "password"="1234") ,     	
    	//那就应该调用 setName和setPassword方法
    	// 实例化对象
    	String className = (String) action.get("class");
    	Class<?> clazz = getActionClassByClassName(className);
		Object actionObject = buildActionObject(clazz,parameters);
    	
		//2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
    	// 执行访问的方法
    	String result = (String) executeAccessMethod(actionObject,clazz,"execute");
    	
    	//3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
    	//通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
    	//放到View对象的parameters
    	Map<String,Object> parameterMap = getActionObjectParameters(actionObject,clazz);
    	
    	//4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
    	//放到View对象的jsp字段中。
    	String jsp = getViewPath(action,result);
    	View v = buildView(jsp,parameterMap);
    	
    	return v;
    }

    private static Class<?> getActionClassByClassName(String className) {
    	
    	if(className == null || className.trim().equals("")){
    		throw new RuntimeException("没有配置action的class属性");
    	}
    	
    	try {
    		return Class.forName(className);
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
		return null;
	}

	/**
     * 获取配置文件中视图的路径
     * @param action
     * @param result
     * @return
     */
	private static String getViewPath(Map<String, Object> action, String result) {
		
		if(result != null && !result.trim().equals("")){
			
			List<Map<String,Object>> resultList = (List<Map<String, Object>>) action.get("childElementList");
			
			if(resultList != null && !resultList.isEmpty()){
				for (Map<String, Object> map : resultList) {
					String readResult = (String) map.get("name");
					if(result.equals(readResult)){
						Object jsp = map.get("text");
						if(jsp  == null){
				    		throw new RuntimeException("未找到与返回结果[" + result + "]之对应的视图");
				    	}
						return (String) jsp;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 执行访问的方法
	 * @param actionObject 访问的action实例化的对象
	 * @param clazz 访问的action Class
	 * @param methodName 访问的方法名称
	 * @return 方法的执行结果
	 */
	private static Object executeAccessMethod(Object actionObject, Class<?> clazz,String methodName) {
		try {
			Method method = clazz.getMethod(methodName);
			return method.invoke(actionObject);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 执行action对象的getter方法，将执行结果放入map中
	 * @param actionObject 访问的action实例化的对象
	 * @param clazz 访问的action Class
	 * @return
	 */
	private static Map<String, Object> getActionObjectParameters(Object actionObject, Class<?> clazz) {
		
		Map<String, Object> parameterMap = new HashMap<>();
		Object result = null;
		Method[] declaredMethods = clazz.getDeclaredMethods();
		Class<?>[] parameterTypes = null;
		if (declaredMethods != null && declaredMethods.length > 0) {
			try {
				for (Method method : declaredMethods) {
					if (isGetMethod(method)) {
						parameterTypes = method.getParameterTypes();
						result = method.invoke(actionObject, (Object[])parameterTypes);

						String methodName = method.getName();
						// getMessage 截取 M(转小写) + 截取essage
						String subMethodName = Character.toLowerCase(methodName.charAt(3))
								+ methodName.substring(4, methodName.length());

						parameterMap.put(subMethodName, result);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return parameterMap;
	}

	/**
	 * 建立action对象
	 * @param clazz
	 * @param parameters 传过来的参数
	 * @return
	 */
	private static Object buildActionObject(@SuppressWarnings("rawtypes") Class clazz, Map<String,String> parameters) {
		
		Object actionObj = null;
		try {
			actionObj = clazz.newInstance();
			// 给action对象的属性设置传过来的参数值
			setProperties(clazz, actionObj, parameters);
			return actionObj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(actionObj == null){
    		throw new RuntimeException("无法实例化action:");
    	}
		return actionObj;
	}

	/**
	 * 建立View对象
	 * @param jsp 
	 * @param parameters
	 * @return
	 */
	private static View buildView(String jsp,Map<String,Object> parameters) {
		
		View v = new View();
		v.setJsp(jsp);
		v.setParameters(parameters);
		
		return v;
	}

	/**
	 * 通过actionName在action列表中查找对应的action(map)
	 * @param actionName
	 * @param actionMap action列表
	 * @return
	 */
	private static Map<String, Object> findAction(String actionName, Map<String, Map<String, Object>> actionMap) {
		Map<String, Object> action = (actionMap != null && !actionMap.isEmpty()) ? actionMap.get(actionName) : null;
		if(action == null){
    		throw new RuntimeException("访问的action[" + actionName + "]不存在");
    	}
		return action;
	}

	/**
	 * 是否是getter方法
	 * @param method2
	 * @return
	 */
	private static boolean isGetMethod(Method method2) {
		if(method2.getName().startsWith("get")){
			return true;
		}
		return false;
	}
	
	/**
	 * 为Action对象设置属性，也就是执行与参数对应的setter方法
	 * @param clazz
	 * @param actionObj
	 * @param parameters 参数
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static void setProperties(@SuppressWarnings("rawtypes") Class clazz, Object actionObj, Map<String, String> parameters) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if(parameters != null && !parameters.isEmpty()){
			Set<Entry<String, String>> entrySet = parameters.entrySet();
			for (Entry<String, String> entry : entrySet) {
				String key = entry.getKey();
				String value = entry.getValue();
				Method method = clazz.getMethod("set" + Character.toUpperCase(key.charAt(0)) + key.substring(1, key.length()),String.class);
				method.invoke(actionObj, value);
			}
		}
	}

	/**
	 * 解析xml配置文件
	 * @param xmlFilePath
	 * @return
	 */
	private static Map<String,Map<String, Object>> xmlParse(String xmlFilePath) {
		File file = new File(xmlFilePath);
		SAXReader saxReader = new SAXReader();
		
		Map<String,Map<String, Object>> actionMap = new HashMap<>();
		try {
			Document document = saxReader.read(file);
			Element rootElement = document.getRootElement();
			actionMap = readActionElement(rootElement);
			return actionMap;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取action节点元素
	 * @param rootElement
	 * @return
	 */
	private static Map<String, Map<String, Object>> readActionElement(Element rootElement) {
		// 存储所有action的信息
		Map<String, Map<String, Object>> actionMap = new HashMap<>();
		for (Iterator<Element> i = rootElement.elementIterator();i.hasNext();) {
			Element element = i.next();
			Map<String, Object> action = readElement(element );			
			// 设置actionMap key[action的name] value[action map]
			actionMap.put((String) action.get("name"), action);
		}
		return actionMap;
	}
	
	/**
	 * 读取元素信息
	 * @param element
	 * @return
	 */
	private static Map<String, Object> readElement(Element element) {
		// 读属性
		Map<String, Object> map = readAttribute(element);
		String text = readText(element);
		map.put("text", text);
		List<Map<String, Object>> childElementList = new ArrayList<>();
		// 查找子元素
		for(Iterator<Element> iterator = element.elementIterator();iterator.hasNext();){
			childElementList.add(readElement(iterator.next()));
		}
		if(childElementList != null && !childElementList.isEmpty()){
			map.put("childElementList", childElementList);
		}
		return map;
	}

	/**
	 * 读取节点的text "<e>text</e>"
	 * @param element
	 * @return
	 */
	private static String readText(Element element) {
		return element.getText();
	}


	/**
	 * 读取节点的属性值 
	 * @param element
	 * @return map key:属性名称 value：属性值
	 */
	private static Map<String, Object> readAttribute(Element element) {
		Map<String,Object> attrMap = new HashMap<>();
		for (Iterator<Attribute> a = element.attributeIterator();a.hasNext();) {
			Attribute attr = a.next();
			attrMap.put(attr.getName(), attr.getData());
		}
		return attrMap;
	} 
	
}
