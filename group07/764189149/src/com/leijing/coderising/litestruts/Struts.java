package com.leijing.coderising.litestruts;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * @Description: 解析Struts文件、处理action请求
 * @author: leijing
 * @date: 2017年3月3日 下午3:03:38
 */
public class Struts {
	private static String STRUTS_XML = "struts.xml";

	private static Map<String,String> actionsMap = new HashMap<String, String>();
	private static Map<String,String> resultMap = new HashMap<String, String>();
	private Struts(){
		try {
			init();
		} catch (Exception e) {
			System.out.println("Struts init error:"+e.getMessage());
		}

	}

	/**
	 * @Description: 确保配置的是需要的节点
	 * @param xmlName
	 * @param name
	 * @return: void
	 * @author: leijing  
	 * @date: 2017年3月3日 下午3:20:31
	 */
	private void checkTag(String xmlName , String name) throws Exception{
		if(xmlName == null || name == null || !xmlName.equals(name)){
			throw new Exception("xml parse error,"+xmlName+"can not be parsed,"+name+" is required!");
		}
	}
	private void init() throws Exception{
		InputStream inputStream = this.getClass().getResourceAsStream(STRUTS_XML);
		SAXReader reader = new SAXReader();//创建SAXReader对象                
		Document  document = reader.read(inputStream); //用SAXReader对象加载配置文件得到Document对象
		Element struts = document.getRootElement();//得到根节点
		checkTag(struts.getName(), "struts");
		List<Element> actions = struts.elements();//得到所有action节点

		for(Iterator<Element> it = actions.iterator() ; it.hasNext();){//遍历action节点
			Element action = (Element) it.next();
			checkTag(action.getName(), "action");
			Attribute actionName = action.attribute("name");
			Attribute actionClazz = action.attribute("class");
			actionsMap.put(actionName.getText(), actionClazz.getText());
			System.out.println("add to actionsMap:"+actionName.getText()+":"+actionClazz.getText());

			List<Element> results = action.elements();//得到配置的result节点
			for(Iterator<Element> rit = results.iterator() ; rit.hasNext();){//遍历result节点
				Element result = (Element) rit.next();
				checkTag(result.getName(), "result");
				Attribute resultName = result.attribute("name");
				resultMap.put(actionName.getText()+"_"+resultName.getText() , result.getData().toString());
				System.out.println("add to resultMap: result:"+resultName.getText()+",url:"+result.getData());
			}
		}


	}

	public static View runAction(String actionName, Map<String,String> parameters) throws Exception {
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
		if(!actionsMap.containsKey(actionName)){
			throw new Exception("action "+actionName+"not found!");
		}

		String className = actionsMap.get(actionName);
		Class<?> clazz = Class.forName(className);
		Object obj = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		
		for (Field field : fields) {
			field.setAccessible(true);
			if(parameters.containsKey(field.getName())){
				field.set(obj, parameters.get(field.getName()));
			}
		}
		Method execute = clazz.getDeclaredMethod("execute");
		String result = (String) execute.invoke(obj, new Object[]{});
		String jsp = resultMap.get(actionName+"_"+result);
		if(null == jsp){
			throw new Exception("jsp config at action:"+actionName +",result:"+ result+" not found !");
		}
		Field message = clazz.getDeclaredField("message");
		message.setAccessible(true);
		View view = new View();
		view.setJsp(jsp);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("message", message.get(obj));
		view.setParameters(params);
		return view;
	}    
	public static Struts getInstance(){
		return StrutsHoldler.INSTANCE;
	}
	private static class  StrutsHoldler{
		private static Struts INSTANCE = new Struts();//struts对象应该是单例的
	}
}
