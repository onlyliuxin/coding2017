package com.example.litestruts;

import com.example.litestruts.dto.Action;
import com.example.litestruts.dto.ActionResult;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Struts {

  public static View runAction(final String actionName, Map<String, String> parameters) {

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
      //读取配置文件struts.xml
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document document = db.parse(
          "/Users/qilei/idea/coding2017/coding2017/group04/916758663/learn02/src/main/java/com/example/litestruts/struts.xml");
      List<Action> actions = parseActions(document);

      //1. 根据actionName找到相对应的class
      Optional<Action> matchAction = actions.stream()
          .filter(action -> action.getName().equals(actionName)).findFirst();
      if (matchAction.isPresent()) {
        String actionClassName = matchAction.get().getClassName();
        Object instance = Class.forName(actionClassName).newInstance();
        Class<?> clazz = instance.getClass();
        for (Entry<String, String> item : parameters.entrySet()) {
          String key = item.getKey();
          Field field = clazz.getDeclaredField(key);
          field.setAccessible(true);
          field.set(instance, item.getValue());
        }

        Method method = clazz.getDeclaredMethod("execute");
        String invokeResult = (String) method.invoke(instance);
        Optional<ActionResult> actionResultOptional = matchAction.get().getActionResultList().stream()
            .filter(actionResult -> actionResult.getName().equals(invokeResult)).findFirst();
        if (actionResultOptional.isPresent()) {
          String jsp = actionResultOptional.get().getJsp();
          Map<String, String> viewParas = new HashMap<>();
          Method[] declaredMethods = clazz.getDeclaredMethods();
          for (int i = 0; i < declaredMethods.length; i++) {
            Method declaredMethod = declaredMethods[i];
            if (declaredMethod.getName().startsWith("get")) {
              String value = (String) declaredMethod.invoke(instance);
              String key = declaredMethod.getName().substring(3).toLowerCase();
              viewParas.put(key,value);
            }
          }

          View view = new View();
          view.setJsp(jsp);
          view.setParameters(viewParas);
          return view;
        }


      }

      System.out.println("Finished");
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }

    return null;
  }

  private static List<Action> parseActions(Document document) {
    List<Action> actions = new ArrayList<Action>();
    NodeList actionList = document.getElementsByTagName("action");
    for (int i = 0; i < actionList.getLength(); i++) {
      Node item = actionList.item(i);
      NamedNodeMap attributes = item.getAttributes();
      Action action = new Action();
      for (int j = 0; j < attributes.getLength(); j++) {
        Node attr = attributes.item(j);
        String key = attr.getNodeName();
        String value = attr.getNodeValue();
        if (key.equals("name")) {
          action.setName(value);
        } else if (key.equals("class")) {
          action.setClassName(value);
        }
      }

      List<ActionResult> actionResultList = new ArrayList<ActionResult>();
      NodeList actionResultNodes = item.getChildNodes();
      for (int j = 0; j < actionResultNodes.getLength(); j++) {
        Node actionResultNode = actionResultNodes.item(j);
        if (actionResultNode.getNodeType() == Node.ELEMENT_NODE) {
          ActionResult actionResult = new ActionResult();
          Element actionResultElement = (Element) actionResultNode;
          actionResult.setName(actionResultElement.getAttribute("name"));
          actionResult.setJsp(actionResultElement.getFirstChild().getNodeValue());
          actionResultList.add(actionResult);
        }
      }
      action.setActionResultList(actionResultList);
      actions.add(action);
    }
    return actions;
  }

}
