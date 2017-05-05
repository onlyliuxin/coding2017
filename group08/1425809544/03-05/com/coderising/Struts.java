package com.coderising;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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
        String actionClass = "";
        View view = null;
        Element element = null;
        File f = new File("src\\com\\coderising\\struts.xml");
        DocumentBuilder db = null;
        DocumentBuilderFactory dbf = null;
        //存放result
        Map<String, String> returnResult = new HashMap<>();
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();

            //得到一个DOM并返回给document对象
            Document dt = db.parse(f);
            //得到一个elment根元素
            element = dt.getDocumentElement();
            // 获得根节点
            System.out.println("根元素：" + element.getNodeName());
            NodeList childNodes = element.getChildNodes();
//            存放action
            List<Map<String, String>> actionList = new ArrayList<>();


            //遍历节点action,
            for (int i = 0; i < childNodes.getLength(); i++) {
                //获取每一条节点条目
                Node node1 = childNodes.item(i);
                Map<String, String> actionMap = new HashMap<>();
                if ("action".equals(node1.getNodeName())) {
                    String actionClass1 = node1.getAttributes().getNamedItem("class").getNodeValue();
                    String actionName1 = node1.getAttributes().getNamedItem("name").getNodeValue();
                    //放入key=name,value=class
                    actionMap.put(actionName1, actionClass1);
                    //如果action-name == login
                    if (actionName.equals(actionName1)) {
                        //获取子节点集合
                        NodeList nodeDetail = node1.getChildNodes();
                        //遍历action的子节点resutlt
                        for (int j = 0; j < nodeDetail.getLength(); j++) {
                            //获取每一条节点
                            Node detail = nodeDetail.item(j);
//                            判断节点名字是否为result
                            if ("result".equals(detail.getNodeName())) {
                                String resultName = detail.getAttributes().getNamedItem("name").getNodeValue();
                                String resultValue = detail.getTextContent();
                                returnResult.put(resultName, resultValue);
                            }
                        }
                    }
                    //把action加入list,
                    actionList.add(actionMap);
                }
            }
            for (Map<String,String> i : actionList) {
                if (i.containsKey(actionName)){
                    actionClass = i.get(actionName);
                }
            }

            System.out.println("读取xml文件获取类的全名为："+actionClass);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            //获取类
            Class clazz = Class.forName(actionClass);
            System.out.println("通过反射获取获取类：" + clazz);
            //创建对象
            Object instance = clazz.newInstance();//调用无参数构造方法
            System.out.println("反射实例化类的对象为：" + instance);
            //获取属性
            Field[] fs = clazz.getDeclaredFields();
            //用于存储属性
            StringBuffer sb = new StringBuffer();
            //追加最外面的定义
            sb.append(Modifier.toString(clazz.getModifiers()) + " class " + clazz.getSimpleName() + "{\n");
            for (Field field : fs) {
                sb.append("\t");
                sb.append(Modifier.toString(field.getModifiers()) + " ");//属性的修饰符 public/static
                sb.append(field.getType().getSimpleName() + " ");//属性的类型的名字
                sb.append(field.getName() + ";\n");//属性的名字
            }
            sb.append("}");
            System.out.println(sb);

            Method[] methods = clazz.getDeclaredMethods();
            if (!parameters.isEmpty() && parameters.size() > 0) {
                Set nameSet = parameters.keySet();
                for (Method method : methods) {
                    String name = method.getName();
                    if (name.startsWith("set")) {
                        String keyName = name.substring(3);//截取set后面的字符串
                        keyName = keyName.substring(0, 1).toLowerCase() + keyName.substring(1);
                        System.out.println("获取set方法中的属性:\t"+keyName);
                        if (nameSet.contains(keyName)) {
                            //进行赋值操作
                            method.invoke(instance, parameters.get(keyName));
                        }
                    }
                }

                String result = "";//返回值，确认登陆成功还是失败
                String viewJsp = "";//关联的视图
                //遍历所有的方法
                for (Method method1 : methods) {
                    String name = method1.getName();
                    //获取方法名为execute的
                    if (name.equals("execute")) {
                        result = (String) method1.invoke(instance);

                    }
                }
                System.out.println("测试登录成功或失败：\t"+result);//返回值，确认登陆成功还是失败
//              存入成员变量的值
                Map<String, String> classAttrMap = new HashMap<>();
                //通过get方法获取成员属性
                for (Method method2 : methods) {
                    String name = method2.getName();
                    if (name.startsWith("get")) {
                        String keyName = name.substring(3);
                        keyName = keyName.substring(0, 1).toLowerCase() + keyName.substring(1);
                        System.out.println("获取具有get方法的属性"+keyName);
                        //获得值
                        String value = (String) method2.invoke(instance);
                        classAttrMap.put(keyName, value);
                    }
                }
                System.out.println(classAttrMap.get("name") +"\t"+ classAttrMap.get("password") +"\t"+ classAttrMap.get("message"));

                view = new View();

                Set keySet = returnResult.keySet();
                if (keySet.contains(result)){
                    viewJsp = returnResult.get(result);
                    view.setJsp(viewJsp);
                    view.setParameters(classAttrMap);
                }
                System.out.println(view.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }


    public static void main(String[] args) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("name", "test");
        params.put("password", "1234");
        Struts struts = new Struts();
        struts.runAction("login", params);

    }

}
