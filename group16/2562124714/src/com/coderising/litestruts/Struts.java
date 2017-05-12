package com.coderising.litestruts;

import com.sun.org.apache.regexp.internal.RE;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.helpers.DefaultHandler;

import javax.print.Doc;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {

        //0. SAX Parser is faster and uses less memory than DOM parser. Dom解析功能强大，可增删改查，操作时会将xml文档以文档对象的方式读取到内存中，因此适用于小文档
        //Sax解析是从头到尾逐行逐个元素读取内容，修改较为不便，但适用于只读的大文档

        long  lasting = System.currentTimeMillis();

        try {
            File f = new File("C:\\Users\\zhangwj\\Desktop\\java课程\\coding\\coding2017-1\\group16\\2562124714\\src\\com\\coderising\\litestruts\\struts.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);
            NodeList nl = doc.getElementsByTagName("struts");
            for (int i = 0; i < nl.getLength(); i++) {

                Node node = doc.getElementsByTagName("action").item(i); //get action node
                //System.out.print("action name is " + doc.getElementsByTagName("action").item(i).getFirstChild().getNodeValue());
                Element e = (Element) node;
                System.out.printf("attribute of name is "+  e.getAttribute("name") + "  actionName Need is" + actionName);
                if (e.getAttribute("name").toString().equals(actionName)) {
                    //1 获取相应的class 设置用户名和密码
                    // System.out.print("action name is " + e.getAttribute("name") + "  action class is " + e.getAttribute("class"));
                    Class ActionClass = Class.forName(e.getAttribute("class"));
                    //强制类型转换
                    Object Action = ActionClass.newInstance();
                    for (Map.Entry<String, String> entry : parameters.entrySet()
                            ) {
                        if (entry.getKey() == "name") {
                            //设置姓名
                            //2 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
                            Method fun_setName = ActionClass.getDeclaredMethod("setName", String.class);
                            fun_setName.invoke(Action, entry.getValue());

                        } else if (entry.getKey() == "password") {
                            //设置密码
                            Method fun_setName = ActionClass.getDeclaredMethod("setPassword", String.class);
                            fun_setName.invoke(Action, entry.getValue());
                        } else {
                            continue;
                        }
                    }

                    Method ExecuteMethod = ActionClass.getDeclaredMethod("exectue");
                    //2 调用execute方法
                    String ss = "11";
                    Object ExecuteResultValue = ExecuteMethod.invoke(Action);

                    //3通过反射找到对象的所有getter方法（例如 getMessage）,
                    //通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
                    //放到View对象的parameters
                    Method Getter_Method = ActionClass.getDeclaredMethod("getMessage");
                    Object message = Getter_Method.invoke(Action);
                    Map<String, String> messageMap = new HashMap<String, String>();
                    messageMap.put("message", (String) message);
                    com.coderising.litestruts.View view = new com.coderising.litestruts.View();
                    view.setParameters(messageMap);

                    //4 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，
                    //放到View对象的jsp字段中。
                    //首先获取result节点
                    NodeList ResultNL = ((Element) node).getElementsByTagName("result");
                    for (int j = 0; j < ResultNL.getLength(); j++
                            ) {
                        Node node1 = ResultNL.item(j);
                        Element e1 = (Element) node1;
                        System.out.println("name is " + e1.getAttribute("name") + "return Value is" + (String) ExecuteResultValue);
                        if (e1.getAttribute("name").toString().equals((String) ExecuteResultValue)) {
                            view.setJsp(node1.getFirstChild().getNodeValue());
                        }
                    }

                    return view;


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
    	
    	return null;
    }    

}
