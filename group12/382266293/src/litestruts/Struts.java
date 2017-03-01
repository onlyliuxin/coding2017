package litestruts;

import static util.Print.println;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import util.XMLreader;



public class Struts {

    static Object actionObj;
    static Object viewObj;
	
    public static View runAction(String actionName, Map<String,String> parameters) throws IntrospectionException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

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

        View view  = Struts.runAction(actionName,params);        
        
        Assert.assertEquals("/jsp/homepage.jsp", view.getJsp());
        Assert.assertEquals("login successful", view.getParameters().get("message"));
        
        <struts>
    <action name="login" class="com.coderising.action.LoginAction">
        <result name="success">/jsp/homepage.jsp</result>
        <result name="fail">/jsp/showLogin.jsp</result>
    </action>
        */
    	
    	XMLreader reader = new XMLreader();
    	String clazz = reader.parseClass(actionName);
   
    	actionObj = getObj(clazz);
 	
    	setParams(actionObj,parameters);
    	String result = (String) invoke(actionObj,"execute");
    	String viewPage = reader.parseResult(actionName,result);
		Map<String,String> viewParams = getViewParams(actionObj);
		viewParams.put("jsp", viewPage);
		println(viewParams);
		viewObj = getObj("litestruts.View");
		setParams(viewObj,viewParams);
	
    	return (View) viewObj;
    }
    
    
    private static Object getObj(String clazz) {
    	Class cls = null;
    	try {
    		cls  = Class.forName(clazz);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	try {
			return cls.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return cls;
	}


	public static Map getViewParams (Object obj) {
    	
    	PropertyDescriptor[] pd0 = getPropertyDescriptor(obj);
		Map<String,String> viewParams = new HashMap<String,String>();

        for (int i = 0; i < pd0.length; i++) {
        	String readMethod = pd0[i].getReadMethod().getName().substring(3);
        	String value = null;
			try {
				value = (String) pd0[i].getReadMethod().invoke(obj);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
        	viewParams.put(readMethod.toLowerCase(), value);
        }
        return viewParams;
    }

    
    private static PropertyDescriptor[] getPropertyDescriptor(Object obj) {
		
        BeanInfo bi0 = getBeanInfo(obj);
		PropertyDescriptor[] pd0 = bi0.getPropertyDescriptors();
		return pd0;
	}
    
    private static BeanInfo getBeanInfo(Object obj) {
		
        BeanInfo bi0 = null;
		try {
			bi0 = Introspector.getBeanInfo(obj.getClass(), Object.class);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return bi0;
	}
    
    private static void setParams(Object pb0, Map parameters) {
    	
		PropertyDescriptor[] pd0 = getPropertyDescriptor(pb0);
		
        for (int i = 0; i < pd0.length; i++) {
          String name = pd0[i].getName();
          if(parameters.containsKey(name))
			try {
				pd0[i].getWriteMethod().invoke(pb0,parameters.get(name));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
        }
    }
 
    private static String invoke(Object obj, String execute) {
    	BeanInfo bi0 = getBeanInfo(obj);
        MethodDescriptor[] methods = bi0.getMethodDescriptors();
        for (int i = 0; i < methods.length; i++) {
            String methodName = methods[i].getName();
            if(methodName.equals(execute))
				try {
					return (String) methods[i].getMethod().invoke(actionObj);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
        }
        return null;

	}


	public static void main(String args[]) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		Map<String,String> params = new HashMap<String,String>();
        params.put("name","test");
        params.put("password","1234");
 
    	View view = runAction("login",params);
    	println(view.getJsp());
    	

    }

}