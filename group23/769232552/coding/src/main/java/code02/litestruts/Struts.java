package code02.litestruts;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class Struts {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Struts.class);

    private static final Map<String, Class<?>> clazzMap = new HashMap<String, Class<?>>();
    private static final Map<String, String> actionMap = new HashMap<String, String>();
    private static final Map<String, String> pageMap = new HashMap<String, String>();

    //解析xml文件
	private static void parseXML(){
        //读取文件
        File file = new File("src/main/resources/struts.xml");
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();

        for (Iterator<Element> iterator = root.elementIterator("action"); iterator.hasNext();) {
            Element e = iterator.next();
            String actionKey = e.attributeValue("name");
            String actionValue = e.attributeValue("class");
            actionMap.put(actionKey, actionValue);
            for(Iterator<Element> childIterator = e.elementIterator();childIterator.hasNext();){
                Element child = childIterator.next();
                String jspKey = actionKey + "-" + child.attributeValue("name");
                String jspValue = child.getTextTrim();
                pageMap.put(jspKey, jspValue);
            }
        }
    }

    //加载xml文件中的类
    private static void initiateClazz(){
        for (Map.Entry<String, String> entry : actionMap.entrySet()) {
            String actionName = entry.getKey(); //login
            String className = entry.getValue(); //code02.litestruts.LoginAction
            Class<?> cls;
            try {
                cls = Class.forName(className, true, Thread.currentThread().getContextClassLoader());
                clazzMap.put(actionName,cls);
            } catch (Exception e) {
                logger.warn("加载类 " + className + "出错！");
            }
        }
    }

    static {
        parseXML();
        initiateClazz();
    }

    //返回实例对象
    private static Object getInstance(String actionName){
        Object instance = null;
        for (Map.Entry<String, Class<?>> entry : clazzMap.entrySet()) {
            String action = entry.getKey(); //login
            Class<?> cls = entry.getValue(); //code02.litestruts.LoginAction.class
            if(actionName.equals(action)){
                try {
                    instance = cls.newInstance();
                } catch (Exception e) {
                    logger.error("生成实例出错！", e);
                    throw new RuntimeException(e);
                }
            }
        }
        return instance;
    }

    //返回以get开头的方法
    private static List<Method> getMethods(String actionName){
        List<Method> methodsList = new ArrayList<Method>();
        Class<?> cls = clazzMap.get(actionName);
        Method[] methods = cls.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            String methodName = methods[i].getName();
            if(methodName.startsWith("get")){
                methodsList.add(methods[i]);
            }
        }
        return methodsList;
    }


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

    public static View runAction(String actionName, Map<String, String> parameters)  {
        LoginAction loginAction = (LoginAction) getInstance(actionName);
        loginAction.setName(parameters.get("name"));
        loginAction.setPassword(parameters.get("password"));

        String result = loginAction.execute();

        Map params = new HashMap<String, String>();
        List<Method> methods = getMethods(actionName);
        for(Method method : methods){
            String key = method.getName().substring(3);
            String value = null;
            try {
                value = (String) method.invoke(loginAction);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            params.put(key,value);
        }


        View view = new View();

        view.setParameters(params);
        String resultKey = actionName + "-" + result;
        view.setJsp(pageMap.get(resultKey));

        return view;
    }


    	
    


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        String actionName = "login";
        HashMap<String,String> params = new HashMap<String, String>();
        params.put("name","test");
        params.put("password","12345");

        View view = Struts.runAction(actionName,params);
        System.out.println(view.getJsp());
        System.out.println(view.getParameters());


    }

}
