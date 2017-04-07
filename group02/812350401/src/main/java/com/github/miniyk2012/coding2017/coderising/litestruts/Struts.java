package com.github.miniyk2012.coding2017.coderising.litestruts;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.Element;


public class Struts {

	/** dom4j object model representation of a xml document. Note: We use the interface(!) not its implementation */
	private static Document doc;
	private static Element aElement;
	private static Object object;
	private static View view;
	private static final Logger logger = Logger.getLogger(Struts.class.getName());
	
    public static View runAction(String actionName, Map<String,String> parameters) throws DocumentException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, IntrospectionException {

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
    	
    	readXml();
    	String retValue = processAction(actionName, parameters);
    	view = generateView(retValue);
    	return view;
    }    
    
    private static View generateView(String retValue) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	view = new View();
    	Map<String, String> map = getFields();
    	String jsp = getJsp(retValue);
    	view.setParameters(map);
    	view.setJsp(jsp);
		return view;
	}

	private static String getJsp(String retValue) {
		for (Iterator i = aElement.elementIterator( "result" ); i.hasNext();) {
			Element result = (Element) i.next();
			if (result.attributeValue("name").equals(retValue)) {
				return result.getText();
            }
		}
		return "";
	}

	/**
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static Map<String, String> getFields()
			throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		Map<String, String> map = new HashMap<>();
    	Class clazz = object.getClass();  
    	Field[] fields = object.getClass().getDeclaredFields();//获得属性 
    	for (Field field : fields) {  
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(),  
                    clazz);  
            Method getMethod = pd.getReadMethod();//获得get方法  
            String value = (String) getMethod.invoke(object);//执行get方法返回一个Object  
            map.put(field.getName(), value);
        }
		return map;
	}

	private static void readXml() throws DocumentException {
    	String fileName = Thread.currentThread().getContextClassLoader().getResource("").getPath()
    			+ "com/github/miniyk2012/coding2017/coderising/litestruts/struts.xml"; 
    	File aFile = new File(fileName);
    	SAXReader xmlReader = new SAXReader();
    	doc = xmlReader.read(aFile);
    }
    
    private static String processAction(String actionName, Map<String,String> parameters) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
    	generateObject(actionName);
        setFields(parameters);
        return doExecute();
	}

	/**
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static String doExecute() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Class<? extends Object> c = object.getClass();
        Method method = c.getMethod("execute");
        String ret = (String) method.invoke(object);
        return ret;
	}

	/**
	 * @param parameters
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void setFields(Map<String, String> parameters)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		for (Map.Entry<String, String> entry: parameters.entrySet()) {
        	String key = entry.getKey();
        	String value = entry.getValue();
        	key = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
        	Class<? extends Object> c = object.getClass();
        	Method method = c.getMethod(key, String.class);
        	method.invoke(object, value);
        }
	}

	/**
	 * @param actionName
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	private static void generateObject(String actionName)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Element root = doc.getRootElement();
    	String className = null;
    	for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
            Element actionElement = (Element) i.next();
            if (actionElement.attributeValue("name").equals(actionName)) {
            	aElement = actionElement;
            	className = actionElement.attributeValue("class");
            	break;
            }
        }
    	if (className == null) throw new InstantiationException("no such className");
        object = Class.forName(className).newInstance();
	}
    
    
    public static void main(String args[]) throws DocumentException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, IntrospectionException
    {
    	Map<String,String> params = new HashMap<String,String>();
        params.put("name","test");
        params.put("password","1234");
    	View view = runAction("login", params);
    	logger.info(view.toString());
    }
    
}
