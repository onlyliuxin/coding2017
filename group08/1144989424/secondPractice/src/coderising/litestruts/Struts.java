package coderising.litestruts;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader; 



public class Struts {
    
    private static String filePath = "src/coderising/litestruts/struts.xml";
    
    public static void main(String [] args) throws Exception{
        String actionName = "login";
        List<String> list = getClassNameFromXml(filePath,actionName);
        Map<String,String> parameters = new HashMap<String,String>();
        parameters.put("name","test");
        parameters.put("password","1234");
        
        Class clz = Class.forName(list.get(0));
        LoginAction loginAction = (LoginAction)clz.newInstance();
        
        loginAction.setName(parameters.get("name"));
        loginAction.setPassword(parameters.get("password"));
        
        Method[] methods = clz.getMethods();
        String value = "";
        for(Method m : methods){
            if(m.getName().equals("execute")){
                value = (String) m.invoke(loginAction,new Object[0]);
                System.out.println(value);
            }
        }
//        value
        Map loginMap = new HashMap();
        Field[] fields = clz.getDeclaredFields();
        for(Field f : fields){
            loginMap.put(f.getName(), getGetMethod(clz, f.getName()));
        }
        View view = new View();
        if(loginMap != null){
            view.setParameters(loginMap);
            if("success".equals(value)){
                view.setJsp(list.get(1));
            }
            if("fail".equals(value)){
                view.setJsp(list.get(2));
            }
        }
    }

    public static View runAction(String actionName, Map<String,String> parameters){

        /*
         
		0. 读取配置文件struts.xml
		
 		1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
		据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
		("name"="test" ,  "password"="1234") ,     	
		那就应该调用 setName和setPassword方法
		
		2. 通过反射调用对象的execute 方法， 并获得返回值，例如"success"
		
		3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
		放到View对象的parameters
		
		4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
		放到View对象的jsp字段中。
        
        */
        List<String> list = getClassNameFromXml(filePath,actionName);
//        Map<String,String> parameters = new HashMap<String,String>();
//        parameters.put("name","test");
//        parameters.put("password","1234");
        
        Class clz = null;
        try {
            clz = Class.forName(list.get(0));
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        LoginAction loginAction = null;
        try {
            loginAction = (LoginAction)clz.newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        loginAction.setName(parameters.get("name"));
        loginAction.setPassword(parameters.get("password"));
        
        Method[] methods = clz.getMethods();
        String value = "";
        for(Method m : methods){
            if(m.getName().equals("execute")){
                try {
                    value = (String) m.invoke(loginAction,new Object[0]);
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(value);
            }
        }
//        value
        Map loginMap = new HashMap();
        Field[] fields = clz.getDeclaredFields();
        for(Field f : fields){
            loginMap.put(f.getName(), getGetMethod(clz, f.getName()));
        }
        View view = new View();
        if(loginMap != null){
            view.setParameters(loginMap);
            if("success".equals(value)){
                view.setJsp(list.get(1));
            }
            if("fail".equals(value)){
                view.setJsp(list.get(2));
            }
        }
        
    	return view;
    }
    
    /**
     * 
     * @param filePath
     * @param actionName
     * @return  list中有三个参数，第一个类名，第二个success时，第三个fail
     * @throws Exception
     */
    private static List<String> getClassNameFromXml(String filePath,String actionName){
        
        List list  = new ArrayList();
        
        //用dom4j读取xml
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new File(filePath));
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Element rootElement = document.getRootElement();
        List<Element> childElements = rootElement.elements();
        for(Element child : childElements){
            String nodeName = child.attributeValue("name");
            if(actionName.equals(nodeName)){
                String className = child.attributeValue("class");
                list.add(className);
                List<Element> resultElements = child.elements();
                for(Element resultE : resultElements){
                    list.add(resultE.getData());
                    list.add(resultE.getData());
                }
            }
        }
        
        return list;
    }

    
    /** 
     * java反射bean的get方法 
     *  
     * @param objectClass 
     * @param fieldName 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public static Method getGetMethod(Class objectClass, String fieldName) {  
        StringBuffer sb = new StringBuffer();  
        sb.append("get");  
        sb.append(fieldName.substring(0, 1).toUpperCase());  
        sb.append(fieldName.substring(1));  
        try {  
            return objectClass.getMethod(sb.toString());  
        } catch (Exception e) {  
        }  
        return null;  
    }  
    
    /** 
     * java反射bean的set方法 
     *  
     * @param objectClass 
     * @param fieldName 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public static Method getSetMethod(Class objectClass, String fieldName) {  
        try {  
            Class[] parameterTypes = new Class[1];  
            Field field = objectClass.getDeclaredField(fieldName);  
            parameterTypes[0] = field.getType();  
            StringBuffer sb = new StringBuffer();  
            sb.append("set");  
            sb.append(fieldName.substring(0, 1).toUpperCase());  
            sb.append(fieldName.substring(1));  
            Method method = objectClass.getMethod(sb.toString(), parameterTypes);  
            return method;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    } 
    
    /** 
     * 执行set方法 
     *  
     * @param o 执行对象 
     * @param fieldName 属性 
     * @param value 值 
     */  
    public static void invokeSet(Object o, String fieldName, Object value) {  
        Method method = getSetMethod(o.getClass(), fieldName);  
        try {  
            method.invoke(o, new Object[] { value });  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 执行get方法 
     *  
     * @param o 执行对象 
     * @param fieldName 属性 
     */  
    public static Object invokeGet(Object o, String fieldName) {  
        Method method = getGetMethod(o.getClass(), fieldName);  
        try {  
            return method.invoke(o, new Object[0]);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    
}
