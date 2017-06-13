	
	package com.coderising.litestruts;
	
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.Iterator;
	import java.util.Map;
	
	import javax.xml.parsers.ParserConfigurationException;

	import org.xml.sax.SAXException;

	import java.lang.reflect.InvocationTargetException;
	import java.lang.reflect.Method;
		
	
	
	public class Struts {
	
	    public static View runAction(String actionName, Map<String,String> parameters) {
	    	View view=new View();
	    	
	    	String execute=null;	//execute返回值。
	    	String placeOfJsp=null;	//JSP地址。
	    	String classname=getClassName(actionName);
	    	
	    	Class<?> classAction = null;
	    	Object obj=null;
	    	try {
				classAction=Class.forName(classname);		//根据类名反射实例化class类。
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	    	
	    	//ClassAction类使用newInstance();方法调用LoginAction类的默认构造方法创建LoginAction类。
	    	try {
				obj=classAction.newInstance();		//Object类型的对象。
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	    	
	    	//调用对象的setter方法,设置用户名和密码，即把name和password的值设置到当前LoginAction的对象中。
	    	try {
				setter(obj,"name",parameters.get("name"),String.class);
				setter(obj,"password",parameters.get("password"),String.class);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
	    	
	    	//调用对象的exectue方法。
	    	try{
		    	Method method=classAction.getMethod("execute");
				execute=(String) method.invoke(obj);
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	
	    	//调用对象的所有getter方法,把值设置到view对象的parameters属性中。
	    	Map<String, String> para=new HashMap<String, String>();
	    	try{
	    		para.put("name", getter(obj,"name"));
	    		para.put("password", getter(obj,"password"));
	    		para.put("message", getter(obj,"message"));
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	view.setParameters(para);
	    	
	    	//根据execute返回值和<result>配置，将JSP地址赋值给View对象的jsp成员中。
	    	placeOfJsp=getResultName(actionName,execute);
	    	view.setJsp(placeOfJsp);
	    	
	    	//System.out.println(view.getJsp()+" "+view.getParameters());
	    	return view;
	    }    
	    
	    
	    /*
	     * 第一个参数为操作对象，第二个参数为操作的数据的数据成员，第三个参数为set的值，第四个参数为参数的类型
	     */
	    private static void setter(Object obj, String att, Object value, Class<?> type) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
				//实例化一个set方法
				Method method=obj.getClass().getMethod("set"+initStr(att), type);
				method.invoke(obj, value);
		}
	    
	    /*
	     * getter方法
	     */
	    private static String getter(Object obj,String att) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
	    	Method method=obj.getClass().getMethod("get"+initStr(att));
	    	method.invoke(obj);
			return (String) method.invoke(obj);
	    }
	    
	    /*
	     * 根据java的命名规则，数据成员的第一个单词都要小写，其他单词的首字母大写。
	     * 所以把set和get方法后面的单词的首字母大写，如setName中的N
	     * 和getName中的N.
	     */
		private static String initStr(String old) {
			String str=old.substring(0,1).toUpperCase()+old.substring(1);
			//把首字母大写。
			return str;
		}

		public static void main(String args[]){
	    	String actionName = "login";
	    	Map<String,String> params = new HashMap<String,String>();
	        params.put("name","test");
	        params.put("password","1234");
	    	 runAction(actionName,params);
	    }
	    
	    //查找action类,查找result时，必须要通过查找action才行。
	    private static String getClassName(String actionName){
	    	String className=getData(actionName).get(actionName);
	    	
	    	return className;
	    }
	    
	    //查找result时，必须要通过查找action才行。
	    private static String getResultName(String actionName,String result){
	    	String placeOfJsp=getData(actionName).get(result);
	    	
	    	return placeOfJsp;
	    }
	    
	    //从配置信息中获取数据,获取
		private static HashMap<String,String> getData(String actionName) {
			SAXGetInfo a=new SAXGetInfo();
	    	ArrayList<HashMap<String, String>> x;
	    	HashMap<String,String> y=null;
	    	Object[] arr=new Object[10];
	    	int size=0;
	    	
			try {
				x = a.getDate();
				Iterator it=x.iterator();
				while(it.hasNext()){ 
					arr[size]=(Object)it.next();
					size++;
					
				}
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			if(actionName.equals("login")){
				y=(HashMap<String, String>) arr[0];
			}
			if(actionName.equals("logout")){
				y=(HashMap<String, String>) arr[1];
			}
			return y;
		}
	
	}
