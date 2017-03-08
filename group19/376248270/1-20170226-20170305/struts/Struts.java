package struts;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Struts {
	
	private static final String CONFIG_FILE = "struts/struts.xml";
	private static final String SETTER_METHOD_PREFIX = "set";
	private static final String GETTER_METHOD_PREFIX = "get";
	
	private static  Map<String, Action> actionList;
	
	static {
		System.out.println("Loading struts config file: " + CONFIG_FILE);
		actionList = parseConfigFile(CONFIG_FILE);
		System.out.println("Load struts config success!");
	}

	/**
	 * 自测
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("name", "test");
		map.put("password", "1234");
		runAction("login", map);
	}
	
	/**
	 * 运行指定action
	 * @param actionName
	 */
	public static View runAction(String actionName, Map<String,String> parameters) {
		Action action = getActionByName(actionName);
		View view = new View();
		
		try {
			Class cls = Class.forName(action.getClassName());
			Object actionInstance = cls.newInstance();
			
			// 设置参数
			setParameters(cls, actionInstance, parameters);
			
			// 执行execute方法
			String executeResult = execute(cls, actionInstance);
			
			// 获取属性
			String[] attributes = new String[]{"message"};
			Map<String, String> results = getAttributes(cls, actionInstance, attributes);
			
			// 获取JSP
			String resultJsp = getResultJSP(action, executeResult);
			
			// 组装视图
			view.setJsp(resultJsp);
			view.setParameters(results);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return view;
	}
	
	/**
	 * 获取需要返回的JSP名称
	 * @param action
	 * @param executeResult
	 * @return
	 */
	private static String getResultJSP(Action action, String executeResult) {
		Map<String, String> resultMap = action.getResultMap();
		String resultJSP = "";
		
		if (resultMap.containsKey(executeResult)) {
			resultJSP = resultMap.get(executeResult);
		}
		return resultJSP;
	}
	
	/**
	 * 执行action的execute方法
	 * @param cls
	 * @param instance
	 * @return
	 */
	private static String execute(Class cls, Object instance) {
		String result = null;
		try {
			Method method = cls.getMethod("execute");
			result = (String) method.invoke(instance);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 通过getter方法获取实例属性
	 * @param cls
	 * @param instance
	 * @param parameterName
	 * @return
	 */
	private static Map<String, String> getAttributes(Class cls, Object instance, String[] attributes) {
		Map<String, String> result = new HashMap<>();
		
		try {
			for (String attr : attributes) {
				String methodName = GETTER_METHOD_PREFIX + capitalizeFirstLetter(attr);
				Method method = cls.getMethod(methodName);
				String returnValue = (String)method.invoke(instance);
				result.put(attr, returnValue);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 为action实例设置属性
	 * @param cls
	 * @param instance
	 * @param parameters
	 */
	private static void setParameters(Class cls, Object instance, Map<String, String> parameters) {
		for (String name : parameters.keySet()) {
			String methodName = SETTER_METHOD_PREFIX + capitalizeFirstLetter(name);
			try {
				Method method = cls.getMethod(methodName, String.class);
				method.invoke(instance, parameters.get(name));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 将传入的字符串首字母大写
	 * @param inputString
	 * @return
	 */
	private static String capitalizeFirstLetter(String inputString) {
		if (inputString == null || inputString.length() == 0) {
			return inputString;
		}
		
		return inputString.substring(0, 1).toUpperCase() + inputString.substring(1);
	}
	
	/**
	 * 解析struts配置文件
	 * @param configFile
	 * @return
	 */
	private static Map<String, Action> parseConfigFile(String configFile) {
		Map<String, Action> parseResult = new HashMap<>();
		
		try {
			File inputFile = new File(configFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			NodeList nList = doc.getElementsByTagName("action");
			
			for (int i = 0; i < nList.getLength(); i++) {
				Action action = new Action();
				Map<String, String> resultList = new HashMap<>();
				String actionName = "";
				
				Node nNode = nList.item(i);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element)nNode;
					actionName = eElement.getAttribute("name");
					String className = eElement.getAttribute("class");
					
					action.setName(actionName);
					action.setClassName(className);
					
					NodeList nodeList = eElement.getElementsByTagName("result");
					for (int j = 0; j < nodeList.getLength(); j++) {
						String resultName = ((Element)nodeList.item(j)).getAttribute("name");
						String resultValue = ((Element)nodeList.item(j)).getTextContent();
						resultList.put(resultName, resultValue);
					}
					action.setResultMap(resultList);
				}
				
				parseResult.put(actionName, action);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return parseResult;
	}
	
	/**
	 * 返回指定的action
	 * @param actionName
	 * @return
	 */
	private static Action getActionByName(String actionName) {
		return actionList.get(actionName);
	}
	
	private static class Action {
		String name;
		String className;
		Map<String, String> resultMap;
		
		public String getName() {
			return name;
		}
		public String getClassName() {
			return className;
		}
		public Map<String, String> getResultMap() {
			return resultMap;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		public void setResultMap(Map<String, String> resultList) {
			this.resultMap = resultList;
		}
	}

}
