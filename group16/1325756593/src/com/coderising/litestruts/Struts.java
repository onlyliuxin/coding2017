package com.coderising.litestruts;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class Struts {
	
	public static String strutsPath;
	

	public static String getStrutsPath() {
		return strutsPath;
	}

	public static void setStrutsPath(String strutsPath) {
		Struts.strutsPath = strutsPath;
	}




	public static View runAction(String actionName, Map<String,String> parameters) throws ParserConfigurationException, SAXException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {

        /*
         
		0. 读取配置文件struts.xml
 		
 		1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
		据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
		("name"="test" ,  "password"="1234") ,     	
		那就应该调用 setName和setPassword方法
		
		2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		
		
		
		4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
		放到View对象的jsp字段中。
        
        */
		if(strutsPath==null||strutsPath.equals("")){
			strutsPath = initStrutsPath();
		}
    	List<Action> actions = parseXml(strutsPath);
    	Action action = getAction(actions,actionName);  	
    	NotBeNull(action);  	
    	
    	
    	String actionClassName = action.getClassName();
    	Class actionClass =  Class.forName(actionClassName);
    	Object actionClassObject  = actionClass.newInstance();

    	doSetterMethod(parameters, actionClass, actionClassObject);
    	
    	Method excuteMethod = actionClass.getMethod("execute", null);
    	String result = excuteMethod.invoke(actionClassObject, null).toString();
    	String viewPath = action.getResultAndViewMap().get(result);
    	View view = new View();
    	view.setJsp(viewPath);
    	Map<String, Object> parametersMap = doGetterMethod(actionClass, actionClassObject);
    	view.setParameters(parametersMap);
    
    	return view;
    }

	
	/**
	 * 3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
		放到View对象的parameters
	 * @param actionClass
	 * @param actionClassObject
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static Map<String, Object> doGetterMethod(Class actionClass, Object actionClassObject)
			throws IllegalAccessException, InvocationTargetException {
		Map<String,Object> parametersMap = new HashMap<>();
    	for(Method method :actionClass.getMethods()){
    		if(method.getName().startsWith("get")){
    			String getValue =   getMethodValue(method.getName());
    			parametersMap.put(getValue, method.invoke(actionClassObject, null));
    		}    		
    	}
		return parametersMap;
	}

	/**
	 * 1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
		据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
		("name"="test" ,  "password"="1234") ,     	
		那就应该调用 setName和setPassword方法
	 * @param parameters
	 * @param actionClass
	 * @param actionClassObject
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void doSetterMethod(Map<String, String> parameters, Class actionClass, Object actionClassObject)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		for( Map.Entry<String, String> entry: parameters.entrySet()){
    		String param = entry.getKey();
    		Method methodKeySet = actionClass.getMethod(getMethodSet(param), String.class);
    		methodKeySet.invoke(actionClassObject, entry.getValue());		
    	}
	}

	/**
	 * 初始化strutsPath
	 * @return
	 */
	private static String initStrutsPath() {
		String path = Struts.class.getClassLoader().getResource("").getPath();
    	String strutsXmlPath = path+"/com/coderising/litestruts/struts.xml";
		return strutsXmlPath;
	}   
    

	/**
	 * 通过name构造setName
	 * @param name
	 * @return
	 */
	public static String getMethodSet(String name){
	   String firstChar = name.substring(0, 1).toUpperCase();
	   return "set"+firstChar+name.substring(1);
	}
	
	/**
	 * 获取getMessage对应的message
	 * @param name
	 * @return
	 */
	public static String getMethodValue(String name){
		if(name.length()>=3){
			name = name.substring(3);
		}
		 String firstChar = name.substring(0, 1).toLowerCase();
		  return firstChar+name.substring(1);
		}
	
   
    
    /**
     * 将xml解析成为Action的List结构
     * @param xmlPath
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static List<Action> parseXml(String xmlPath) throws ParserConfigurationException, SAXException, IOException{
    	List<Action> retActionList = new ArrayList<>();
    	File xmlFile = new File(xmlPath);
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder = factory.newDocumentBuilder();
    	Document doc = builder.parse(xmlFile);
    	NodeList actionLists = doc.getElementsByTagName("action");
    	for(int i=0;i<actionLists.getLength();i++){
    		Node currentNode= actionLists.item(i);
    		Action action = new Action();
    		
    		String actionName = getAttribute(currentNode,"name");
    		String actionClass = getAttribute(currentNode,"class");
    		action.setActioName(actionName);
    		action.setClassName(actionClass);
    		HashMap<String, String> resultAndViewMap=getResultAndViewNodeList(currentNode);
    		action.setResultAndViewMap(resultAndViewMap);
    		retActionList.add(action);
    	}
    	return retActionList;
    }

    /**
     * 获取该Node对应的result和对应的jsppath
     * @param currentNode
     * @param resultAndViewMap
     */
	private static HashMap<String, String> getResultAndViewNodeList(Node currentNode) {
		Map<String, String> resultAndViewMap = new HashMap<>();
		NodeList resultAndViewNodeList =   currentNode.getChildNodes();
		
		for(int j=0;j<resultAndViewNodeList.getLength();j++){
			Node resultAndViewNode= resultAndViewNodeList.item(j);
			if("result".equals(resultAndViewNode.getNodeName())){
				String result = getAttribute(resultAndViewNode,"name");
				String viewPath = resultAndViewNode.getTextContent();
				NotBeNull(result);
				NotBeNull(viewPath);
				resultAndViewMap.put(result, viewPath);   	
			}    					
		}
		return (HashMap<String, String>) resultAndViewMap;
	}

	
	private static void NotBeNull(Object result) {
		if(result==null){
			throw new IllegalArgumentException();
		}
	}
	

    
    public static String getAttribute(Node node ,String name){
    	NamedNodeMap nameNodeMap = node.getAttributes();
    	NotBeNull(nameNodeMap);
    	Node nameNode = nameNodeMap.getNamedItem(name);
    	NotBeNull(nameNode);
		String actionName =nameNode.getNodeValue();
		return actionName;
    }
    
    public static Action getAction(List<Action> actions ,String actionName){
    	for(Action action:actions){
    		if(action.getActioName().equals(actionName)){
    			return action;
    		}
    	}
		return null;
    	
    }
    
    

}
