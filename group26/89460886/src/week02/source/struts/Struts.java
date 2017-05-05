package coding.coderising.litestruts;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author jiaxun
 */
public class Struts {

    public static View runAction(String actionName, Map<String, String> parameters) {
        if (StringUtils.isEmpty(actionName)) return null;
        DOMParser parser = new DOMParser();
        Document document = parser.parse("src/coding/coderising/litestruts/struts.xml");
        Element rootElement = document.getDocumentElement();

        NodeList actionList = rootElement.getElementsByTagName("action");
        if (actionList == null || actionList.getLength() == 0) return null;

        for (int i = 0, len = actionList.getLength(); i < len; i++) {
            Element element = (Element) actionList.item(i);
            String name = element.getAttribute("name");
            if (actionName.equals(name)) {
                return handleAction((Element) actionList.item(i), parameters);
            }
        }
        return null;
    }

    private static View handleAction(Element element, Map<String, String> parameters) {
        String className = element.getAttribute("class");
        try {
            Class clazz = Class.forName(className);
            Object object = clazz.newInstance();

            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String methodStr = "set" + entry.getKey().toUpperCase().substring(0, 1) +
                        entry.getKey().substring(1);
                Method method = clazz.getMethod(methodStr, String.class);
                method.invoke(object, entry.getValue());
            }

            Method execute = clazz.getMethod("execute");
            String result = (String) execute.invoke(object);

            Map<String, String> getParams = new HashMap<>();
            Field[] fields = clazz.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (int i = 0, len = fields.length; i < len; i++) {
                    PropertyDescriptor pd = new PropertyDescriptor(fields[i].getName(), clazz);
                    Method getMethod = pd.getReadMethod();
                    if (getMethod != null) {
                        getParams.put(fields[i].getName(), (String) getMethod.invoke(object));
                    }
                }
            }

            NodeList nodeList = element.getElementsByTagName("result");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element resultElement = (Element) nodeList.item(i);
                String name = resultElement.getAttribute("name");
                if ("success".equals(name) && Constants.ACTION_SUCCESS.equals(result)) {
                    return createView(resultElement, getParams);
                }
                if ("failure".equals(name) && Constants.ACTION_FAILURE.equals(result)) {
                    return createView(resultElement, getParams);
                }
                if ("error".equals(name) && Constants.ACTION_ERROR.equals(result)) {
                    return createView(resultElement, getParams);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static View createView(Element element, Map<String, String> parameters) {
        View view = new View();
        view.setJsp(element.getTextContent());
        view.setParameters(parameters);
        return view;
    }

}
