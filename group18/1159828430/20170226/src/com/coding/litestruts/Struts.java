package com.coding.litestruts;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author Scholar
 * @Time：2017年2月27日 下午8:49:47
 * @version 1.0
 */
public class Struts {

	public static View runAction(String actionName, Map<String,String> parameters) {

    	try {//多行代码需要try catch时，如何合理使用try cath和throw Exception？
    		Element actionElement = getElement("action", actionName);
    		//实例化action并设置参数
    		String actionAdress = actionElement.attribute("class").getText();
    		Class<?> actionClass = Class.forName(actionAdress);
    		Object actionObj = actionClass.newInstance();
		   	for (Entry<String, String> entry : parameters.entrySet()) {
				Method setMethod = actionClass.getMethod("set"+initStr(entry.getKey()), String.class);
				setMethod.invoke(actionObj, entry.getValue());
			}
		   	
		   	//执行execute
		    Method executeMethod = actionClass.getMethod("execute");
			//Type returnType = m.getGenericReturnType();//获取返回值类型
			String returnValue = (String)executeMethod.invoke(actionObj);//这里如何根据返回类型来动态接收方法的返回值？
			//selenium
			//获取返回值放到view
			Map<String,String> params = new HashMap<String,String>();
			Field[] fields = actionClass.getDeclaredFields();
			for (Field field : fields) {
				Method m = actionClass.getMethod("get"+initStr(field.getName()));
				params.put(field.getName(), (String) m.invoke(actionObj));
			}
			Class<?> viewClass = View.class;
			Object viewObj = viewClass.newInstance();
			Field paramField = viewClass.getDeclaredField("parameters");
			paramField.setAccessible(true);
			paramField.set(viewObj, params);
			
			//将对应result的值赋值给view并返回view对象
			Element resultElement = getElement("result", returnValue);
			String jsp = resultElement.getStringValue();
			Method setJsp = viewClass.getMethod("setJsp", String.class);
			return (View)setJsp.invoke(viewObj, jsp);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
    
    //DOM4J结合XPATH解析XML,取得相应的节点
    private static Element getElement(String node, String name){
    	Document document;
    	Element element = null;
    	try {
    		File file = new File("src/com/coding/litestruts/struts.xml");
    		SAXReader reader = new SAXReader();
			document = reader.read(file);
			element = (Element) document.selectSingleNode("//"+ node + "[@name='" + name + "']");
		} catch (Exception e) {
			System.out.println("读取XML失败");
		}
		return element;
    }
    
    // 将单词的首字母大写  
    public static String initStr(String old){  
        String str = old.substring(0,1).toUpperCase() + old.substring(1) ;  
        return str ;  
    } 
}