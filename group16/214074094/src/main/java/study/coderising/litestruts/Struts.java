package study.coderising.litestruts;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import study.coderising.litestruts.bean.Action;
import study.coderising.litestruts.bean.Result;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class Struts {

    private static final String EXECUTE = "execute";

    public static View runAction(String actionName, Map<String, String> parameters)
            throws DocumentException, ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, InstantiationException {

        //0. 读取配置文件struts.xml
        SAXReader reader = new SAXReader();
        Document doc = reader.read("./src/main/resources/struts.xml");
        Element root = doc.getRootElement();
        Action action = getAction(actionName, root);

        //1. 根据actionName找到相对应的class
        Class clz = Class.forName(action.getClassPath());
        //获取一个实例,方便接下来对同一个对象赋值
        Object obj = clz.newInstance();

        //1.1 据parameters中的数据，调用对象的setter方法
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String setFunctionName = getFunctionName("set", entry.getKey());
            Method set = clz.getDeclaredMethod(setFunctionName, String.class);
            set.invoke(obj, entry.getValue());
        }

        //2. 通过反射调用对象的exectue方法， 并获得返回值
        Method execute = clz.getDeclaredMethod(EXECUTE);
        String response = execute.invoke(obj).toString();

        //3. 通过反射找到对象的所有getter方法并调用， 把值和属性形成一个HashMap
        Method[] methods = clz.getDeclaredMethods();
        Map<String, Object> map = new HashMap<>();
        for (Method m : methods) {
            if (m.getName().startsWith("get")) {
                String paramName = m.getName().replaceFirst("get", "");
                map.put(paramName.toLowerCase(), m.invoke(obj));
            }
        }

        //3.1 放到View对象的parameters
        View view = new View();
        view.setParameters(map);

        //4. 根据struts.xml中的配置,以及execute的返回值，确定jsp，放到View对象的jsp字段中。
        for (Result result : action.getResults()) {
            if (response.equalsIgnoreCase(result.getResult())) {
                view.setJsp(result.getJumpPath());
                break;
            }
        }

        return view;
    }

    /**
     * @Author: shane
     * @Time: 2017/3/4 23:53
     * @Email: stevenchenguang@gmail.com
     * @param: begin, key
     * @Return: String
     * @Throw:
     * @Desc: 根据开始名称和key获取方法名
     * e.g.: begin: get, key: name, return getName
     */
    private static String getFunctionName(String begin, String key) {
        if (key == null || "".equals(key)) {
            return null;
        }
        StringBuffer sb = new StringBuffer(begin);
        if (key.length() < 2) {
            sb.append(key.toUpperCase());
        } else {
            String first = String.valueOf(key.charAt(0));
            sb.append(first.toUpperCase());
            sb.append(key.substring(1, key.length()));
        }
        return sb.toString();
    }

    /**
     * @Author: shane
     * @Time: 2017/3/4 23:55
     * @Email: stevenchenguang@gmail.com
     * @param: actionName, node
     * @Return: Action
     * @Throw:
     * @Desc: 根据actionName和xml节点获取Action
     */
    private static Action getAction(String actionName, Element node) {
        Action action = new Action();

        Iterator<Element> iterator = node.elementIterator();
        boolean flag = false;
        while (iterator.hasNext()) {
            Element e = iterator.next();
            List<Attribute> list = e.attributes();

            //遍历属性节点
            for (Attribute attr : list) {
                if ("name".equalsIgnoreCase(attr.getName())) {
                    if (!actionName.equalsIgnoreCase(attr.getValue())) {
                        continue;
                    } else {
                        flag = true;
                    }
                    action.setName(attr.getValue());
                }
                if ("class".equalsIgnoreCase(attr.getName())) {
                    action.setClassPath(attr.getValue());
                }

                List<Result> results = new ArrayList<>();

                Iterator<Element> it = e.elementIterator();
                while (it.hasNext()) {
                    Result result = new Result();
                    Element el = it.next();
                    result.setResult(el.attribute(0).getValue());
                    result.setJumpPath(el.getText());
                    results.add(result);
                }
                action.setResults(results);
            }
            if (flag) {
                break;
            }
        }
        return action;
    }

}
