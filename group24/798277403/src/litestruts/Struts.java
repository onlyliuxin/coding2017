package litestruts;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
public class Struts {

    public static View runAction(String actionName, Map<String, String> parameters)  {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        Class<?> actionClass = null;
        LoginAction loginAction = null;
        View view = new View();
        try {
            db = documentBuilderFactory.newDocumentBuilder();
            Document document = db.parse("src/litestruts/struts.xml");
            NodeList nodeList = document.getElementsByTagName("action");

            //遍历每一个action节点
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                //获取action节点的所有属性集合
                NamedNodeMap attrs = node.getAttributes();
                //获取name结点的值
                String nodeName = attrs.getNamedItem("name").getNodeValue();

                if(nodeName.equals(actionName)){
                    //获取LoginAction实例
                    actionClass = Class.forName(attrs.getNamedItem("class").getNodeValue());
                    loginAction = (LoginAction) actionClass.newInstance();

                    //设置用户名密码属性
                    Set<Map.Entry<String, String>> entrySet = parameters.entrySet();
                    for (Map.Entry<String, String> entry : entrySet) {
                        if (entry.getKey().equals("name")) {
                            loginAction.setName(entry.getValue());
                        }
                        if (entry.getKey().equals("password")) {
                            loginAction.setPassword(entry.getValue());
                        }
                    }

                    //执行execute()方法
                    String result = loginAction.execute();

                    //将message封装到view
                    String message = loginAction.getMessage();
                    Map<String,String> map = new HashMap<String,String>();
                    map.put("message",message);
                    view.setParameters(map);

                    //解析对应的result节点
                    NodeList childNodes = node.getChildNodes();
                    //遍历childNodes获取每个节点的节点名和节点值
                    for (int k = 0; k < childNodes.getLength(); k++) {
                        Node childNode = childNodes.item(k);
                        //区分出text类型的node以及element类型的node
                        if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                            NamedNodeMap attributes = childNode.getAttributes();
                            String nodeValue = attributes.getNamedItem("name").getNodeValue();
                            if(nodeValue.equals(result)){
                                view.setJsp(childNode.getTextContent());
                            }
                        }

                    }

                }

            }
        } catch (ParserConfigurationException | SAXException | IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return view;
    }

/*    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = null;
    Class<?> actionClass = null;
    LoginAction loginAction = null;
        try {
        db = documentBuilderFactory.newDocumentBuilder();
        Document document = db.parse("src/week2/litestruts/struts.xml");
        NodeList nodeList = document.getElementsByTagName("action");
        System.out.println("一共有" + nodeList.getLength() + "个结点");
        //遍历每一个action节点
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            //获取action节点的所有属性集合
            NamedNodeMap attrs = node.getAttributes();
            //遍历action的属性
            for (int j = 0; j < attrs.getLength(); j++) {
                //通过item(index)方法获取book节点的某一个属性
                Node attr = attrs.item(j);
                String name = attrs.getNamedItem("name").getNodeValue();
                System.out.println("++++++++++"+name);
                //获取属性名
                System.out.print("属性名：" + attr.getNodeName());
                //获取属性值
                System.out.println("--属性值" + attr.getNodeValue());
                if(attr.getNodeName().equals(actionName)){
                    actionClass = Class.forName(attr.getNodeValue());
                    loginAction = (LoginAction) actionClass.newInstance();
                }
            }
            //解析book节点的子节点
            NodeList childNodes = node.getChildNodes();
            //遍历childNodes获取每个节点的节点名和节点值
            for (int k = 0; k < childNodes.getLength(); k++) {
                //区分出text类型的node以及element类型的node
                if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                    //获取了element类型节点的节点名
                    System.out.print(childNodes.item(k).getNodeName());
                    //获取了element类型节点的节点值
                    System.out.println("--节点值是：" + childNodes.item(k).getFirstChild().getNodeValue());
                    System.out.println("--节点值是：" + childNodes.item(k).getTextContent());
                }
            }
        }
    } catch (ParserConfigurationException | SAXException | IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
    }*/
}