package litestruts;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Struts {

    public static View runAction(String actionName, Map<String, String> parameters) {

        /*
         
		0. 读取配置文件struts.xml
 		
 		1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
		据parameters中的数据，调用对象的setter方法，例如parameters中的数据是
		("name"="test" ,  "password"="1234") ,     	
		那就应该调用 setName和setPassword方法
		
		2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		
		3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
		放到View对象的parameters
		
		4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
		放到View对象的jsp字段中。
        
        */

        //读取配置文件

        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read("src/litestruts/struts.xml");
            Element root = document.getRootElement();
            Element temp = null;

            /*
            *保存root的子节点
            Map<String, Integer> lables = new HashMap();

            for (int i = 0; i < root.elements().size(); i++) {
                temp = (Element) root.elements().get(i);
                lables.put(temp.attribute(0).getText(), i);
            }
            */

            Map<String, String> viewResult = new HashMap<>();
            Map<String, String> resultLable = new HashMap<>();
            Map<String, Integer> firstLayorNode = new HashMap<>();

            //保存根节点的子节点
            for (int i = 0; i < root.elements().size(); i++) {
                temp = (Element)root.elements().get(i);
                firstLayorNode.put(temp.attribute("name").getText(),i);
            }

            View view = new View();

            Element actionLable = (Element)root.elements().get(firstLayorNode.get(actionName));

            if (actionName.equals("login")) {


                for (int i = 0; i < actionLable.elements().size(); i++) {
                    temp = (Element) actionLable.elements().get(i);
                    resultLable.put(temp.attribute("name").getText(), temp.getText());
                }


                Class clazz = Class.forName(actionLable.attribute("class").getText());

                LoginAction la = (LoginAction) clazz.newInstance();
                la.setName(parameters.get("name"));
                la.setPassword(parameters.get("password"));

                Method m = clazz.getMethod("execute");
                String result = (String) m.invoke(la);


                view.setJsp(resultLable.get(result));


                m = clazz.getMethod("getMessage");
                result = (String) m.invoke(la);
                viewResult.put("message", result);

                m = clazz.getMethod("getName");
                result = (String) m.invoke(la);
                viewResult.put("name", result);

                m = clazz.getMethod("getPassword");
                result = (String)m.invoke(la);
                viewResult.put("password", result);

                view.setParameters(viewResult);
                return view;
            }


        } catch (DocumentException e) {
            e.printStackTrace();
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
