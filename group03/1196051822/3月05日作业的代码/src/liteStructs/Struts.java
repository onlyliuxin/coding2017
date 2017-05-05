
import com.byhieg.utils.bexception.UncheckedException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


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

        //创建文件对象，ClassName，view对象等必备的对象以及得到params参数中所有的key-value
        String fileName = "/Users/byhieg/IdeaProjects/learnjava/src/com/byhieg/coding2017/homework305/struts.xml";
        String className;
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        Iterator iterator = parameters.entrySet().iterator();
        String[] methodNames = new String[parameters.size()];
        int i = 0;

        View view = new View();
        Map map = new HashMap();
        // 得到set方法的方法名
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            String key = entry.getKey();
            keys.add(key);
            values.add(entry.getValue());
            methodNames[i++] = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
        }

        try {
            //反射得到对象
            Element element = getTargetElement(actionName, fileName);
            className = getClassName(element);
            Class clz = Class.forName(className);
            Object obj = clz.newInstance();

            //根据上面的set方法名的数组，依次调用，
            for (int j = 0; j < methodNames.length; j++) {
                Method method = clz.getMethod(methodNames[j], String.class);
                method.invoke(obj, values.get(j));
            }

            //执行execute方法
            Method method = clz.getMethod("execute");
            String result = (String) method.invoke(obj);

            //得到所有方法，判断哪些是get方法，是的话，生成需要的map
            Method[] methods = clz.getMethods();
            for (Method item : methods) {
                if (item.getName().contains("get")) {
                    String key = item.getName().substring(3).toLowerCase();
                    Object value = item.invoke(obj);
                    map.put(key, value);
                }
            }

            view.setParameters(map);

            //根据result得到jsp，放入view对象中
            String jsp = getJsp(element, result);
            view.setJsp(jsp);

            return view;

        } catch (DocumentException e) {
            System.out.println("文件找不到");
        } catch (ClassNotFoundException e) {
            System.out.println("找不到指定的类");
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("创建对象失败");
        } catch (NoSuchMethodException e) {
            System.out.println("方法创建失败");
        } catch (InvocationTargetException e) {
            System.out.println("方法执行失败");
        }


        return view;
    }


    /**
     *
     * @param element 指定的的节点
     * @return 返回该节点对应的ClassName的值
     * @throws DocumentException 文件异常
     */

    private static String getClassName(Element element) throws DocumentException {
        return element.attribute(1).getValue();


    }

    /**
     *
     * @param actionName actionName
     * @param fileName xml文件路径
     * @return 指定actionName对应的节点元素
     * @throws DocumentException 文件异常
     */
    private static Element getTargetElement(String actionName, String fileName) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(fileName));
        Element rootNode = document.getRootElement();
        List<Element> elements = rootNode.elements();
        for (Element item : elements) {
            if (actionName.equals(item.attribute(0).getValue())) {
                return item;
            }
        }
        return null;
    }

    /**
     * 通过result，得到指定的JSP
     * @param element actionName对象的节点
     * @param result result标签的name
     * @return result标签的值
     */
    private static String getJsp(Element element,String result) {
        List<Element> elements = element.elements();
        for (Element e : elements) {
            if (result.equals(e.attribute(0).getValue())){
                return e.getTextTrim();
            }
        }

        return null;
    }


}
