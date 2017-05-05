package struts;

import org.dom4j.Element;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by william on 2017/3/4.
 */
public class Struts {


    public static View runAction(String actionName, Map<String, String> parameters) {
        Element root = StrutsUtils.getRoot("struts/struts.xml");
        View view = new View();
        if (root != null) {
            Element selectedEle = (Element) root.selectSingleNode("//action[@name='" + actionName + "']");
            if (selectedEle != null) {

                Class clazz = genClass(selectedEle.attributeValue("class"));
                Object target = setValue(parameters, clazz);

                String result;
                try {
                    result = (String) clazz.getMethod("execute").invoke(target);
                } catch (Exception e) {
                    throw new RuntimeException("invoke execute have some error", e);
                }

                Map<String, Object> response = getValue(clazz, target);
                view.setParameters(response);
                Element selectedResult = (Element) root.selectSingleNode("//action[@name='" + actionName + "']//result[@name='" + result + "']");
                view.setJsp(selectedResult == null ? null : selectedResult.getText());
            }
        }
        return view;
    }


    private static Class genClass(String className) {
        Class clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    private static Object setValue(Map<String, String> parameters, Class clazz) {
        try {
            Object target = clazz.newInstance();
            if (!StrutsUtils.isEmpty(parameters)) {
                for (Map.Entry<String, String> entry : parameters.entrySet()) {
                    String key = entry.getKey();
                    if (!StrutsUtils.isEmpty(key)) {
                        String setterName = new StringBuilder("set").append(key.substring(0, 1).toUpperCase()).append(key.substring(1)).toString();
                        clazz.getMethod(setterName, String.class).invoke(target, entry.getValue());
                    }
                }
            }
            return target;
        } catch (Exception e) {
            throw new RuntimeException("create class instance have some error ", e);
        }
    }

    private static Map<String, Object> getValue(Class clazz, Object target) {
        Map<String, Object> resultsMap = new HashMap<String, Object>();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String fieldName = method.getName();
            if (fieldName.startsWith("get") && !fieldName.equals("getClass")) {
                try {
                    Object value = method.invoke(target);
                    resultsMap.put(new StringBuilder(fieldName.substring(3, 4)).append(fieldName.substring(4)).toString(), value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return resultsMap;
    }


}
