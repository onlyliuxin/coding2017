package com.coderising.action;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

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
//通过JDOM来读取xml文件
public class TestMain {
	
	private static String xmlSource=System.getProperty("user.dir")+"\\src\\com\\coderising\\action\\struts.xml";
	private static Map<String, Object> container = new HashMap<String, Object>();//获取xml中name字符串跟class类
	private static Map<String, String> containerStr = new HashMap<String, String>();//获取xml中name跟class字符串
	private static Map<String, String> containerBak = new HashMap<String, String>();//获取getter方法属性
	
	private static View view;
	private static String resultStr;
	private static LoginAction lAction;
	
	public static void main(String[] args) throws JDOMException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		
		//构造器
		SAXBuilder builder=new SAXBuilder();
		//StringReader reader=new StringReader(System.getProperty("user.dir")+"\\src\\com\\second\\xml/struts.xml");
		
		Document document=builder.build(xmlSource);
		
		XPathFactory xFactory=XPathFactory.instance();
		XPathExpression<Element> expr=xFactory.compile("//struts//action",Filters.element());
		
		List<Element> links=expr.evaluate(document);
		for(Element linkElement:links){
			String name=linkElement.getAttributeValue("name");
			String clazz=linkElement.getAttributeValue("class");
			//利用反射生成实例 然后装进容器
			Object o=Class.forName(clazz).newInstance();
			container.put(name,o);
			containerStr.put(name, clazz);
			
			System.out.println("name: "+name+", class: "+clazz);
		}
		
		/**************
		 3.通过反射找到对象的所有getter方法（例如 getMessage）,  
		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
		放到View对象的parameters
		 */
		if(container.containsKey("login")){
			lAction=(LoginAction)container.get("login");
			lAction.setName("test");
			lAction.setPassword("1234");
			
			resultStr=lAction.execute();//执行execute方法
			System.out.println("name="+lAction.getName()+" password="+lAction.getPassword()+"->登录结果："+resultStr);
			
			Class clazz=Class.forName(containerStr.get("login"));
			Method[] methods=clazz.getMethods();
			for(Method method:methods){
				String methodName=method.getName();
				//int indexTmp=methodName.indexOf("get");
				//if(indexTmp!=-1){
				if(methodName.startsWith("get")){
					//包含get的方法名
					//String methodStr=methodName.substring(indexTmp+3);
					String methodStr=methodName.substring(3);
					System.out.println(methodStr);
					
					//System.out.println(method.invoke(lAction));
					
					containerBak.put(methodName, (String)method.invoke(lAction));
				}
			}
			view.setParameters(containerBak);//保存到view中
		}
		
		/**************
		 4.根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
		放到View对象的jsp字段中
		 */
		
	}
}
