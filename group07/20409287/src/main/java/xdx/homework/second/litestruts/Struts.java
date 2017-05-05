package xdx.homework.second.litestruts;

import org.jcp.xml.dsig.internal.dom.DOMEnvelopedTransform;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class Struts {

    private static Map<String, LoginAction> actionMap;

    private static Map<String, Map<String, String>> actionResultMap;

    private static void init() {

        if (actionMap != null) return;

        actionMap = new HashMap<>();
        actionResultMap = new HashMap<>();
        Document strutsDocument = getDocumentFromFile();
        if (strutsDocument == null) throw new RuntimeException("xml文件读取错误,请检查文件路径是否正确!");

        try {
            NodeList nodeList = strutsDocument.getFirstChild().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeType() != 1) continue;
                Element child = (Element) nodeList.item(i);
                Class actionClass = Class.forName(child.getAttribute("class"));
                actionMap.put(child.getAttribute("name"), (LoginAction) actionClass.newInstance());

                NodeList resultList = child.getElementsByTagName("result");
                Map<String, String> params = new HashMap<>();
                for (int j = 0; j < resultList.getLength(); j++) {
                    Element result = (Element) resultList.item(j);
                    params.put(result.getAttribute("name"), result.getTextContent());
                }
                actionResultMap.put(child.getAttribute("name"), params);
            }

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("xml文件内容有误!");
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("无法创建接口或者抽象类的实例!");
        }
    }

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
        // 读取struts文件并初始化相关类
        init();

        // 根据actionName找到相对应的class
        LoginAction loginAction = actionMap.get(actionName);
        if (loginAction == null) {
            System.out.println("找不到该action: " + actionName);
            return null;
        }

        View view = new View();
        // 据parameters中的数据调用相应的方法
        Class clazz = loginAction.getClass();
        try {
            for (String key : parameters.keySet()) {
                String value = parameters.get(key);
                if (value == null || value.isEmpty()) continue;
                PropertyDescriptor descriptor = new PropertyDescriptor(key, clazz);
                descriptor.getWriteMethod().invoke(loginAction, value);
            }
            // 调用对象的exectue方法， 并获得返回值
            String executeReturn = loginAction.execute();
            // 通过反射找到对象的所有getter方法, 把值和属性形成一个HashMap ,放到View对象的parameters
            PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
            HashMap<String, String> params = new HashMap<>();
            for (PropertyDescriptor pd : pds) {
                params.put(pd.getName(), pd.getReadMethod().invoke(loginAction).toString());
            }
            view.setParameters(params);
            // 确定哪一个jsp
            view.setJsp(actionResultMap.get(actionName).get(executeReturn));
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return view;
    }


    private static Document getDocumentFromFile() {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            URL fileURL = Struts.class.getResource("struts.xml");
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            document = documentBuilder.parse(new File(fileURL.getFile()));
        } catch (ParserConfigurationException e) {
            System.out.println("配置转化异常: " + e.getMessage());
        } catch (SAXException e) {
            System.out.println("SAX读取异常: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO异常: " + e.getMessage());
        }
        return document;
    }

}
