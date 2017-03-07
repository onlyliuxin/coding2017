package coderising.litestruts;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Struts {

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
    public static View runAction(String actionName, Map<String, String> parameters) {

        SAXReader reader = new SAXReader();
        View view = new View();

        try {
            //读取xml，获取class，获取对象
            Document document = reader.read(new File("src\\main\\java\\coderising\\litestruts\\struts.xml"));
            Node node = document.selectSingleNode("//struts/action[@name='" + actionName + "']");
            Element element = (Element) node;
            String className = element.attribute("class").getText();
            Class<?> clazz = Class.forName(className);
            Object object = clazz.newInstance();

            //调用set方法设置属性
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String methodName = "set" + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1);
                Method method = clazz.getMethod(methodName, entry.getValue().getClass());
                method.invoke(object, entry.getValue());
            }

            //调用exectue 方法， 并获得返回值
            Method methodExecute = clazz.getMethod("execute");
            String executeResult = methodExecute.invoke(object).toString();

            //找到所有getter，调用并将值放到resultMap中
            Map<String, String> resultMap = new HashMap<String, String>();
            Method[] getMethods = clazz.getMethods();
            for (Method getMethod : getMethods) {
                int index = getMethod.toString().lastIndexOf(".");
                String pureName = getMethod.toString().substring(index + 1);
                if (pureName.startsWith("get")) { //eg. getName()
                    String tempStr= pureName.substring(3); //eg. Name()
                    String attributeName = tempStr.substring(0, 1).toLowerCase() + tempStr.substring(1, tempStr.length() - 2); //eg. name
                    String attributeResult = getMethod.invoke(object).toString();
                    resultMap.put(attributeName, attributeResult);
                }
            }

            //组装返回对象
            Node resultNode = node.selectSingleNode("result[@name='" + executeResult + "']");
            String resultTarget = resultNode.getText();
            view.setJsp(resultTarget).setParameters(resultMap);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            return view;
        }
    }

    public static void main(String[] args) {

    }

}
