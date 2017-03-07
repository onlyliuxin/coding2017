package litestruts;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {

        try {
            // parse xml file
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse("src/litestruts/struts.xml");
            doc.getDocumentElement().normalize();

            // get action items
            NodeList list = doc.getElementsByTagName("action");
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                Element element = (Element) node;
                // look for action-related class
                if  (element.getAttribute("name").equals(actionName)){

                    Class<?> c = Class.forName(element.getAttribute("class"));
                    Object o = c.newInstance();

                    //set name
                    Method setName = c.getDeclaredMethod("setName", String.class);
                    setName.invoke(o, parameters.get("name"));

                    //set password
                    Method setPassword = c.getDeclaredMethod("setPassword", String.class);
                    setPassword.invoke(o, parameters.get("password"));

                    //execute
                    Method execute = c.getDeclaredMethod("execute", null);
                    // login result
                    String result = execute.invoke(o).toString();

                    //get login messsage
                    Method getMessage = c.getDeclaredMethod("getMessage", null);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("message", getMessage.invoke(o).toString());

                    // new view with parameter map
                    View view = new View();
                    view.setParameters(map);
                    NodeList list1 = element.getElementsByTagName("result");
                    for (int j = 0; j < list1.getLength(); j++) {
                        Node node1 = list1.item(j);
                        Element element1 = (Element) node1;
                        if (element1.getAttribute("name").equals(result)) {
                            view.setJsp(node1.getTextContent());
                        }
                    }
                    return view;
                }
            }
        } catch (Exception e) {
            System.out.println("parse error");
        }
    	return null;
    }
}
