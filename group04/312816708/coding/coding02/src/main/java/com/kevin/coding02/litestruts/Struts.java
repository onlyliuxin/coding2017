package com.kevin.coding02.litestruts;

import com.kevin.coding02.model.ActionModel;
import com.kevin.coding02.model.ResultModel;
import com.kevin.coding02.util.SaxUtil;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Struts {

    public static View runAction(String actionName, Map<String, String> parameters) {
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
        View view = new View();
        try {
            //创建sax解析工厂
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //获取Sax解析器
            SAXParser saxParser = factory.newSAXParser();
            //获取xml读取器
            XMLReader xmlReader = saxParser.getXMLReader();
            //设置内容处理器
            SaxUtil saxUtil = new SaxUtil();
            xmlReader.setContentHandler(saxUtil);
            //读取xml
            xmlReader.parse("src/main/java/com/kevin/coding02/litestruts/struts.xml");

            List<ActionModel> actions = saxUtil.getActions();
            for (ActionModel action : actions) {
                if (actionName.equals(action.getActionName())) {
                    String actionClass = action.getActionClass();
                    Class<?> clazz = Class.forName(actionClass);

                    Object obj=clazz.newInstance();

                    for (String key : parameters.keySet()) {
                        if ("name".equals(key)) {
                            Method setNameMethod = clazz.getDeclaredMethod("setName", String.class);
                            setNameMethod.invoke(obj, parameters.get(key));
                        }
                        if ("password".equals(key)) {
                            Method setPasswordMethod = clazz.getDeclaredMethod("setPassword", String.class);
                            setPasswordMethod.invoke(obj, parameters.get(key));
                        }
                    }

                    Method executeMethod = clazz.getDeclaredMethod("execute");

                    String flag = (String) executeMethod.invoke(obj);

                    Map<String, String> map = new HashMap<String, String>();

                    for (ResultModel result : action.getResults()) {
                        if (flag.equals(result.getName())) {
                            view.setJsp(result.getValue());
                            Method getMessage = clazz.getDeclaredMethod("getMessage");
                            map.put("message", String.valueOf(getMessage.invoke(obj)));
                            view.setParameters(map);
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

}
