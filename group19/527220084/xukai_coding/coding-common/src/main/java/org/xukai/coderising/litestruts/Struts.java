package org.xukai.coderising.litestruts;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xukai.coderising.util.ReflectUtil;
import org.xukai.coderising.util.XmlParseHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Struts {

    private static final ConcurrentHashMap<String,Object> action= new ConcurrentHashMap<String,Object>();

    private static String strutsPath = Thread.currentThread().getContextClassLoader().getResource("struts.xml").getPath().substring(1);

    private static List<Action> actions;

    static {
        try {
            actions = initalStruts();
            System.out.println(actions.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static View runAction(String actionName, Map<String,String> parameters) {
        if (actionName != null && !actionName.equals("")) {
            for(Action action : actions){
                //根据请求参数定位到需要执行的action
                if (action.getName().equals(actionName)) {
                    Class<?> cls = action.getaClass();
                    View view = new View();
                    try {
                        //创建action实例
                        Object obj = ReflectUtil.newInstance(cls);
                        for(Map.Entry<String,String> entry : parameters.entrySet()){
                            ReflectUtil.setValue(obj,entry.getKey(),entry.getValue());
                        }
                        Method method = ReflectUtil.getMethod(cls,"execute");
                        if (method != null) {
                            //执行execute方法
                            Object result = ReflectUtil.invokeMethod(obj, method);
                            if (result instanceof String) {
                                Map<String, String> mapping = action.getResultMapping();
                                //根据映射关系将值放入
                                view.setJsp(mapping.get(result));
                                List<Method> gets = ReflectUtil.getMethodBeginWith(cls, "get");
                                HashMap<String, String> models = new HashMap<String,String>();
                                for(Method getMethod : gets){
                                    //调用所有getter方法
                                    Object getFieldResult = ReflectUtil.invokeMethod(obj, getMethod);
                                    if (result instanceof String) {
                                        String getFieldName = StringUtils.lowerCase((String) getMethod.getName()).substring(3);
                                        models.put(getFieldName,(String) getFieldResult);
                                    }
                                }
                                view.setParameters(models);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        view.setJsp("500");
                    }
                    return view;
                }
            }
        }
        //请求的参数不正确或者没有对应实例返回404
        View view = new View();
        view.setJsp("404");
        return view;
    }

    private static List<Action> initalStruts() throws ClassNotFoundException, DocumentException {
        SAXReader sr = new SAXReader();//获取读取方式

        Document doc = sr.read(strutsPath);
        XmlParseHelper helper = new XmlParseHelper(doc);
        List<Element> actions = helper.getNodeByPath("//action");
        ArrayList<Action> actionsList = new ArrayList<Action>();
        for (Element action : actions){
            Action obj = new Action();
            String nameAttr = helper.getNodeAttrValue(action, "name");
            String classAttr = helper.getNodeAttrValue(action, "class");
            obj.setName(nameAttr);
            obj.setaClass(Class.forName(classAttr));
            List<Element> results = helper.getChildNodeByName(action, "result");
            HashMap<String, String> map = new HashMap<String,String>();
            for (Element result : results){
                String resultNameAttr = helper.getNodeAttrValue(result, "name");
                String resultValue = helper.getNodeValue(result);
                map.put(resultNameAttr,resultValue);
            }
            obj.setResultMapping(map);
            actionsList.add(obj);
        }

        return actionsList;
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


}
