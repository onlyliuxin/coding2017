/*

0. 读取配置文件struts.xml

 1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
("name"="test" ,  "password"="1234") ,
那就应该调用 setName和setPassword方法

2. 通过反射调用对象的execute方法， 并获得返回值，例如"success"

3. 通过反射找到对象的所有getter方法（例如 getMessage）,
通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
放到View对象的parameters

4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，
放到View对象的jsp字段中。

*/

package com.coderising.litestruts;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Struts {
    public static View runAction(String actionName, Map<String,String> parameters) {
        String filePath = Struts.class.getResource("struts.xml").getPath();
        XMLReader reader = null;
        View view = new View();

        try{
            reader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        }catch (SAXException | ParserConfigurationException e){
            e.printStackTrace();
        }

        try{
            StrutsContentHandler handler = new StrutsContentHandler(actionName, parameters, (executeResult, jsp) -> {
                // step4: Set fields for View object in the callback.
                view.setParameters(executeResult);
                view.setJsp(jsp);
            });

            // step0: Load "struts.xml";
            if (reader != null) {
                reader.setContentHandler(handler);
                reader.parse(filePath);
            }

        }catch ( IOException | SAXException e){
            e.printStackTrace();
        }

        // step4: return View object
        return view;
    }
}

class StrutsContentHandler extends DefaultHandler{
    private String actionName;
    private Map<String, String> parameters;
    private StrutsCallback callback;

    private String className;
    private boolean foundClass;
    private String resultName;
    private HashMap<String, String> resultJspMap = new HashMap<>();

    // Constructor to read in 2 parameters
    StrutsContentHandler(String actionName, Map<String, String> parameters, StrutsCallback callback){
        this.actionName = actionName;
        this.parameters = parameters;
        this.callback = callback;
    }

    // The SAX Event handler, event "startElement" is fired when reader reaches a char of "<"
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(Objects.equals(qName, "action")){
            if(Objects.equals(attributes.getValue("name"), actionName)){
                // step1: Parse XML and get class name. And prepare to use reflection.
                className = attributes.getValue("class");
                foundClass = true;
            }else{
                foundClass = false;
            }
        }else if(Objects.equals(qName, "result") && foundClass){
            resultName = attributes.getValue("name");
        }
    }

    // The SAX Event handler to read innerText in XML(String between ">" and "<")
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(foundClass) {
            String jsp = new String(ch, start, length);
            // Important, the "characters" event is also fired for every line warp, as the characters starts with "\n "
            if(!jsp.substring(0, 1).equals("\n")){
                resultJspMap.put(resultName, jsp);
            }
        }
    }

    // The SAX Event handler, event "endDocument" is fired when reader finishes reading the XML
    @Override
    public void endDocument() throws SAXException {
        reflection(className, parameters, resultJspMap);
    }

    // step1,2,3: Using reflection to invoke methods
    private void reflection(String className, Map<String, String> parameters, HashMap<String, String> resultJspMap){
        // Class
        Class loginActionClass = null;

        // Methods
        Method setName = null;
        Method setPassword = null;
        Method execute = null;
        Method getName = null;
        Method getPassword = null;
        Method getMessage = null;

        // Instance of the class for invoking methods
        Object loginAction = null;

        // Result variables after invoking
        String executeResult;
        HashMap<String, String> getterResult;

        // Get class
        try{
            loginActionClass = Class.forName(className);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        // Get instance and methods
        try{
            if (loginActionClass != null) {
                loginAction = loginActionClass.newInstance();
            }

            setName = loginActionClass.getDeclaredMethod("setName", String.class);
            setPassword = loginActionClass.getDeclaredMethod("setPassword", String.class);
            execute = loginActionClass.getDeclaredMethod("execute");
            getName = loginActionClass.getDeclaredMethod("getName");
            getPassword = loginActionClass.getDeclaredMethod("getPassword");
            getMessage = loginActionClass.getDeclaredMethod("getMessage");
        }catch ( NoSuchMethodException| InstantiationException | IllegalAccessException e){
            e.printStackTrace();
        }

        // Invoke method
        try{
            // step1
            invokeSetters(parameters, setName, setPassword, loginAction);
            // step2
            executeResult = invokeExecute(execute, loginAction);
            // step3
            getterResult = invokeGetters(getName, getPassword, getMessage, loginAction);
            // step4
            String jsp = dispatchJsp(executeResult, resultJspMap);

            // Callback the getterResult and jsp for setting up View object
            callback.onStrutsActionGet(getterResult, jsp);
        }catch (IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
    }

    // step1: Invoke setName() and setPassword().
    private static void invokeSetters(Map<String, String> parameters, Method setName, Method setPassword, Object loginAction) throws IllegalAccessException, InvocationTargetException {
        setName.invoke(loginAction, parameters.get("name"));
        setPassword.invoke(loginAction, parameters.get("password"));
    }

    // step2: Invoke execute() and get return value.
    private static String invokeExecute(Method execute, Object loginAction) throws IllegalAccessException, InvocationTargetException {
        return (String)execute.invoke(loginAction);
    }

    // step3: Invoke getters and return results with a HashMap
    private static HashMap<String, String> invokeGetters(Method getName, Method getPassword, Method getMessage, Object loginAction) throws IllegalAccessException, InvocationTargetException {
        String name = (String)getName.invoke(loginAction);
        String password = (String)getPassword.invoke(loginAction);
        String message = (String)getMessage.invoke(loginAction);

        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("password", password);
        parameters.put("message", message);
        return parameters;
    }

    // step4: Dispatch jsp path according to XML configurations and result of execute()
    private static String dispatchJsp(String executeResult, HashMap<String, String> resultJspMap){
        return resultJspMap.get(executeResult);
    }
}

interface StrutsCallback{
    void onStrutsActionGet(HashMap<String, String> map, String jspPath);
}
