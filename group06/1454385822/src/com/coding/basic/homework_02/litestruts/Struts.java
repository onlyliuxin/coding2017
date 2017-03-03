package com.coding.basic.homework_02.litestruts;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class Struts {


	private static String jspUrl = null;
	private static String resultStatic = null;
	
    public static View runAction(String actionName, Map<String, String> params) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, DocumentException{

        /*
         
		0. 读取配置文件struts.xml
 		
 		1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
		根据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
		("name"="test" ,  "password"="1234") ,     	
		那就应该调用 setName和setPassword方法
		
		2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		
		3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
		放到View对象的parameters
		
		4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
		放到View对象的jsp字段中。
        
        */
    	
    	View view = new View();
		Element root = getRoot();
		Class clazz = getClazz(actionName, root);
		Object obj = clazz.newInstance();
		
		methodInvoke(clazz, obj, params);
		String result = getExecuteInfo(clazz, obj);
		setParams(clazz, obj, view);
		getJsp(result, root, actionName);
		view.setJsp(jspUrl);

    	return view;
    }    

    /**
     * 读取xml文件获得根节点
     * @return
     * @throws DocumentException 
     */
    private static Element getRoot() throws DocumentException{
    	//step1:创建SAXReader对象
		SAXReader reader = new SAXReader();
		//step2:读取文件 转换成Document
		Document document = reader.read("src/com/coding/basic/homework_02/litestruts/struts.xml");
		return document.getRootElement();
    }
    
    
    /**
     * 根据给定的actionName找到对应的Class
     * @return
     * @throws ClassNotFoundException 
     */
    @SuppressWarnings("rawtypes")
	private static Class getClazz(String actionName, Element node) throws ClassNotFoundException{

		findClassNameByAttr(node, actionName);
		if(resultStatic != null)
			return Class.forName(resultStatic);

    	throw new ClassNotFoundException();
    }
    
    /**
     * 根据resultName找到对应的jsp路径
     * @param resultName
     * @param node
     * @return
     */
    @SuppressWarnings("unchecked")
	private static void getJsp(String resultName, Element node, String actionName){
    	
    	if(node.attributes() != null)
    		forEachAttr(node.attributes(), actionName, node, resultName);
    	
    	List<Element> listElement = node.elements();
    	for(Element e : listElement)
    		getJsp(resultName, e, actionName);

    	if(jspUrl != null)
    		return;
    }
    
    /**
     * 遍历当前结点的属性
     * @param list
     * @param actionName
     * @param node
     * @param resultName
     */
    private static void forEachAttr(List<Attribute> list, String actionName, Element node, String resultName){
    	List<Attribute> attrs = node.attributes();
		for(Attribute attr : attrs){
			if(resultName.equals(attr.getValue()) && !"".equals(node.getTextTrim()))			
				findJspByParentNode(actionName, node);
		}
    }
     
    /**
     * 根据跟定的action找到对应的jspUrl
     * @param actionName
     * @param node
     */
    private static void findJspByParentNode(String actionName, Element node){
    	Element parent = node.getParent();
		if(parent.attributes() != null){
			for(Attribute pattr : (List<Attribute>)parent.attributes()){
				if(actionName.equals(pattr.getValue()))
					jspUrl = node.getTextTrim();
			}
		}
    }
    
    /**
     * 获取execute()方法运行后的信息
     * @param clazz
     * @param obj
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    private static String getExecuteInfo(Class clazz, Object obj) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
    	Method executeMethod = clazz.getMethod("execute", null);
		
		return (String)executeMethod.invoke(obj, null);
		
    }
    
    /**
     * 将类中的getter信息放入View
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */
    public static View setParams(Class clazz, Object obj, View view) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
    	Map<String, String> viewMap = getterAttr(clazz, obj);
		view.setParameters(viewMap);
		return view;
    }
    
    /**
     * 找出当前对象的所有getter方法，将信息放入map中
     * @param clazz
     * @return
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */
    private static Map<String, String> getterAttr(Class clazz, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
    	Map<String, String> viewMap = new HashMap<String, String>();
		//获取所有的getter方法
		Method[] methods = clazz.getDeclaredMethods();
		for(Method me : methods){
			if("get".equals(me.getName().substring(0, 3)))
				viewMap.put(method2Attr(me), (String) me.invoke(obj, null));
		}
		return viewMap;
    }
    
    /**
     * 将方法名转换为属性名
     * @param method
     * @return
     */
    private static String method2Attr(Method method){
    	StringBuilder builder = new StringBuilder();
    	return builder.append(new Character(method.getName().charAt(3)).toString().toLowerCase())
    			.append(method.getName().substring(4)).toString();
    }
    
    /**
     * 调用setXXX给属性设置值
     * @param params
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */
    private static void methodInvoke(Class clazz, Object obj,Map<String, String> params) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
    	//将参数名转换为方法名
		Map<String, String> methodMap = methodMap(params);
		
		//将数据set到属性中
		Iterator<Map.Entry<String, String>> it = methodMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, String> entry = it.next();
			Method method = clazz.getMethod(entry.getKey(), String.class);
			method.invoke(obj, entry.getValue());
		}
    }
    
    /**
     * 将属性名转换为方法名之后放入到map中
     * @param params
     * @return
     */
	@SuppressWarnings("rawtypes")
	private static Map methodMap(Map<String, String> params){
    	Map<String, String> methodMap = new HashMap<String, String>();
		Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, String> entry = it.next();
			String attrName = entry.getKey();
			StringBuilder builder = new StringBuilder();
			builder.append("set").append(new Character(attrName.charAt(0)).toString().toUpperCase()).append(attrName.substring(1));
			methodMap.put(builder.toString(), entry.getValue());
		}
		return methodMap;
    }
    
    
    /**
     * 遍历结点的属性找到类名
     * @param attrList
     * @return   List<Attribute> attrList
     * @throws ClassNotFoundException 
     */
    @SuppressWarnings("unchecked")
	private static void findClassNameByAttr(Element node,String actionName) throws ClassNotFoundException{
    	
    	if(!node.attributes().isEmpty())
    		For2attr(actionName, node);

    	List<Element> listElement = node.elements();
    	for(Element e : listElement)
    		findClassNameByAttr(e, actionName);

    	if(resultStatic != null)
    		return;
    }
    
    /**
     * 遍历属性找到类名
     * @param actionName
     * @param findAction
     * @param node
     */
    private static void For2attr(String actionName, Element node){
    	boolean findAction = false;
    	for(Attribute attribute : (List<Attribute>)node.attributes()){
			
			if(actionName.equals(attribute.getValue())){
				findAction = true;
				break;
			}
		}
		for(Attribute attribute : (List<Attribute>)node.attributes()){
    		if(findAction == true && "class".equals(attribute.getName())){
    			resultStatic = attribute.getValue(); 
    			return;
			}
    	}
    }
    
    
}
