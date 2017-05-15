package com.pan.litestruts;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
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
        try {
            Document document = JaxpDomUtil.getDocument();
            NodeList actionNodeList = document.getElementsByTagName("action");
            for (int i = 0; i < actionNodeList.getLength(); i++) {
                NamedNodeMap attributes = actionNodeList.item(i).getAttributes();
                String methodName = attributes.getNamedItem("name").getTextContent();
                if (!actionName.equals(methodName)) {
                    continue;
                }
                // 获取全类名对象，反射创建对象
                String className = attributes.getNamedItem("class").getTextContent();
                Class<?> actionClass = Class.forName(className);

                // 获取反射的方法名称, 因为是public修饰所以用的是getMethod，获取私有方法需要用 getDeclaredMethod
                Method setName = actionClass.getMethod("setName", String.class);
                Method setPassword = actionClass.getMethod("setPassword", String.class);
                // 创建对象
                Object actionObject = actionClass.newInstance();

                // 调用反射的setter方法,给参数赋值
                setName.invoke(actionObject, parameters.get("name"));
                setPassword.invoke(actionObject, parameters.get("password"));

                // 获取execute方法
                Method execute = actionClass.getMethod("execute");
                // 返回结果
                String result = (String) execute.invoke(actionObject);

                // 获取getMessage方法
                Method getMessage = actionClass.getMethod("getMessage");
                String message = (String) getMessage.invoke(actionObject);
                // 创建一个Map 用来放置在 View中
                Map<String, String> params = new HashMap<String, String>();
                params.put("message", message);

                // 获取返回的JSP路径，这个需要比较result节点的name属性
                //获取action的子节点
                NodeList resultNodes = actionNodeList.item(i).getChildNodes();
                String viewUrl = "";
                for (int n = 0; n < resultNodes.getLength(); n++) {
                    Node item = resultNodes.item(n);
                    NamedNodeMap resultAttributes = item.getAttributes();
                    if (resultAttributes == null) {
                        continue;
                    }
                    String name = resultAttributes.getNamedItem("name").getTextContent();
                    if (result.equals(name)) {
                        viewUrl = item.getTextContent();
                        break;
                    }
                }
                View view = new View();
                view.setJsp(viewUrl);
                view.setParameters(params);
                return view;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;

    }

}
