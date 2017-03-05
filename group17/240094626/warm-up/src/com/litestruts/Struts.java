package com.coderising.litestruts;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.coderising.litestruts.exception.StrutsRunActionException;
import com.coderising.litestruts.exception.StrutsXMLLoaderException;



/**
 * 模拟Struts读取xml文件，解析ation，并执行exectue方法
 * @author 240094626
 *
 */
public class Struts {
	/**单例对象*/
	private static Struts instance = null;
	/**默认配置文件目录*/
	private static final String STRUTS_CONFIG_DIR = "src/com/coderising/litestruts/";
	/**默认配置文件名*/
	private static final String STRUTS_CONFIG_XML = "struts.xml";
	/**默认编码字符集*/
	private static final String ENCODE = "UTF-8";
	/**action文档节点名称*/
	private static final String DOC_ACTION = "action";
	/**result文档节点名称*/
	private static final String DOC_ACTION_RESULT = "result";
	
	/**
	 * 单例实现，双check
	 * @return
	 */
	public static Struts getInstance(){
		if(instance == null){
			synchronized (Struts.class) {
				if(instance == null){
					instance = new Struts();
				}
			}
		}
		return instance;
	}

    public View runAction(String actionName, Map<String,String> parameters) throws StrutsRunActionException  {

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
    	View view = new View();
		try {
			// 0. 读取配置文件struts.xml
			Map<String,Action> actions = getActionByDom4J();
			
			// 1. 找到LoginAction 反射实例化，setName 和setPassword
			Action action  = actions.get(actionName);
			Class<?> clz = Class.forName(action.clazz);
			Object actionObj = clz.newInstance();
			Method m1 = clz.getDeclaredMethod("setName", String.class);
			m1.setAccessible(true);
			m1.invoke(actionObj, parameters.get("name"));
			Method m2 = clz.getDeclaredMethod("setPassword", String.class);
			m2.setAccessible(true);
			m2.invoke(actionObj, parameters.get("password"));
			
			// 2.通过反射调用对象的exectue 方法
			Method mExectue = clz.getDeclaredMethod("execute", null);
			String result = (String) mExectue.invoke(actionObj, null);
			
			// 3. 通过反射找到对象的所有getter方法,通过反射来调用， 把值和属性形成一个HashMap
			Field[] fs = clz.getDeclaredFields();
			Map<String,Object> pMap = new HashMap<String,Object>(10);
			for(Field f : fs){
				String methodName = "get"+toUpperCase(f.getName());
				Method m = clz.getDeclaredMethod(methodName, null);
				m.setAccessible(true);
				pMap.put(f.getName(), m.invoke(actionObj, null));
			}
			view.setParameters(pMap);
			// 4.根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp,
			view.setJsp(action.results.get(result));
			System.out.println(view);
		} catch (StrutsXMLLoaderException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw new StrutsRunActionException("Action执行失败",e);
		}
    	
    	
    	return view;
    }  
    
    
    /**
     * dom4j读xml
     * @return
     * @throws StrutsXMLLoaderException
     */
    private Map<String,Action> getActionByDom4J() throws StrutsXMLLoaderException{
    	return getActionByDom4J(STRUTS_CONFIG_DIR,STRUTS_CONFIG_XML,ENCODE);
    }
    /**
     * dom4j读xml
     * @param dir
     * @param fileName
     * @param encode
     * @return
     * @throws StrutsXMLLoaderException
     */
    private Map<String,Action> getActionByDom4J(String dir,String fileName,String encode) throws StrutsXMLLoaderException {
    	File f = new File(dir);
    	if(!f.exists()){
    		throw new StrutsXMLLoaderException("路径不存在："+dir);
    	}
    	f = new File(f.getPath()+"\\"+fileName);
    	if(!f.exists()){
    		throw new StrutsXMLLoaderException("路径："+dir+"中文件不存在："+fileName);
    	}
    	SAXReader saxReader = new SAXReader();
    	saxReader.setEncoding(encode);
    	Map<String,Action> actions = new HashMap<String,Action>(10);
    	try {
			Document document = saxReader.read(f);
			Element root =document.getRootElement();
			Iterator<Element> it = root.elementIterator(DOC_ACTION);
			while(it.hasNext()){
				Action action = new Action();
				Element element = it.next();
				action.name = element.attributeValue("name");
				action.clazz = element.attributeValue("class");
				Map<String,String> results = new HashMap<String,String>(10);
				Iterator<Element> rIt = element.elementIterator(DOC_ACTION_RESULT);
				while(rIt.hasNext()){
					Element rElement = rIt.next();
					results.put(rElement.attributeValue("name"), rElement.getText());
				}
				action.results = results;
				actions.put(action.name, action);
			}
		} catch (Exception e) {
			throw new StrutsXMLLoaderException("xml文件解析失败",e);
		}
    	
    	return actions;
    }
    
    /**
     * 首个字符大写
     * @param fieldName
     * @return
     */
    private String toUpperCase(String fieldName){
    	return  fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
    }
    
    
    
    /**
     * Action类
     */
    private static class Action{
    	String name;
    	String clazz;
    	Map<String,String> results;
    }
    
    

}
