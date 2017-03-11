package com.coderising.litestruts;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {

        //读取xml
        DocumentBuilderFactory documentBuilderFactory =  DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
            URL xmlPath = Struts.class.getClassLoader().getResource("struts.xml");


//            Document doc = db.parse("src/main/struts.xml");
            Document doc = db.parse(xmlPath.toString());
            NodeList actionNodeList = doc.getElementsByTagName("action");

            Map<String,String> resultMap = new HashMap<String, String>();


            Map<String,Class<?>> actionMap = new HashMap<String,Class<?>>();
            for(int i=0;i<actionNodeList.getLength();i++)
            {
                Node actionNode = actionNodeList.item(i);
                Element action = (Element)actionNode;
                String xmlActionName = action.getAttribute("name");
                String xmlActionClassName = action.getAttribute("class");
                Class<?> actionClass = Class.forName(xmlActionClassName);
                actionMap.put(xmlActionName,actionClass);

                NodeList resultNodeList  = action.getElementsByTagName("result");
                for(int j =0 ;j<resultNodeList.getLength(); j++)
                {
                    Element resultNode = (Element)resultNodeList.item(j);
                        System.out.println(resultNode.getAttribute("name")+ ":" + resultNode.getTextContent());
                        if(xmlActionName.equals(actionName))
                        {
                            resultMap.put(resultNode.getAttribute("name"),resultNode.getTextContent());

                        }

                }




            }


            Object classInstance = actionMap.get(actionName).newInstance();
            Method[] methods = actionMap.get(actionName).getDeclaredMethods();
            Map <String,Method> methodMap = new HashMap<String, Method>();
            for(int i = 0;i<methods.length;i++){
                methodMap.put(methods[i].getName(),methods[i]);
            }
            for(String key:parameters.keySet())
            {
                char [] arrtCharArry = key.toCharArray();
                arrtCharArry[0]-=32;
                String  attrName = String.valueOf(arrtCharArry);
                String setMethodName = "set"+attrName;
                String getMethodName = "get"+attrName;
                methodMap.get(setMethodName).invoke(classInstance,parameters.get(key));
                Object getAtriBuObj = methodMap.get(getMethodName).invoke(classInstance);
                System.out.println(getAtriBuObj.toString());
            }

            String resultType = (String)methodMap.get("execute").invoke(classInstance);
            Object message = methodMap.get("getMessage").invoke(classInstance);

            View view = new View();
            view.setJsp(resultMap.get(resultType));
            Map<String,String> messageMap = new HashMap<String, String>();
            messageMap.put("message",message.toString());
            view.setParameters(messageMap);
            return  view;





        } catch (Exception e) {
            e.printStackTrace();
        }





        return null;
    }

    public static void main(String[] args) {

        Map<String,String> parametersMap = new HashMap<String, String>();
        parametersMap.put("name","test");
        parametersMap.put("password","1234");


        View view= runAction("login",parametersMap);
        System.out.println(view.getJsp());

    }

}
