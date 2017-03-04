package com.basic.struts;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Struts {
	
	public static View runAction(String actionName,Map<String,String> parameters){
		/*
		 * 0. 读取配置文件struts.xml
		 * 1. 根据actionName找到相对应的class, 例如LoginAction,通过反射实例化（创建对象）,
		 * 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 ("name"="test","password"="1234"),那就应该调用 setName和setPassword方法
		 * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		 * 3. 通过反射找到对象的所有getter方法（例如 getMessage）, 
		 *  通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,放到View对象的parameters
		 * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp,放到View对象的jsp字段中。 
		 */
		DocumentBuilderFactory domfac=DocumentBuilderFactory.newInstance();//得到 DOM 解析器的工厂实例    
		try {
			DocumentBuilder domBuilder=domfac.newDocumentBuilder();//从 DOM 工厂获得 DOM 解析器 
			InputStream is= new  FileInputStream(new File("../struts.xml"));
			Document doc = domBuilder.parse(is);
			Element root =  doc.getDocumentElement();//获取根节点
			NodeList childNodes = root.getChildNodes();//获取子节点<action>
			if(childNodes!=null){
				for(int i=0;i<childNodes.getLength();i++){//遍历子节点<action>
					Node childNode = childNodes.item(i);
					if(childNode.getNodeType()==Node.ELEMENT_NODE){//判断是否是元素节点
						String name = childNode.getAttributes().getNamedItem("name").getNodeValue();//获取节点的属性值（action的属性name的值）
						if(actionName.equals(name)){
							Class<?> class1 = Class.forName(childNode.getAttributes().getNamedItem("class").getNodeValue());//得到action的一个class对象
							Object object = class1.newInstance();//构造一个action实例
							for(Entry<String,String> entry : parameters.entrySet()){
								//得到每个参数的setter方法
								Method method = class1.getMethod(new StringBuffer("set").append(entry.getKey().substring(0,1).toUpperCase()).append(entry.getKey().substring(1)).toString(), entry.getKey().getClass());
								//调用action中的每个参数的setter方法来给action属性赋值
								method.invoke(object,entry.getValue());
							}
							//得到处理action请求的函数入口
							Method method2 = class1.getMethod("execute");
						
						}
						/*for(Node node = childNode.getFirstChild();node!=null;node=node.getNextSibling()){
							if(node.getNodeType()==Node.ELEMENT_NODE){
								if(node.getNodeName().equals("name")){
									String name2 = node.getNodeValue();
									String name3 = node.getFirstChild().getNodeValue();
								}
								if(node.getNodeName().equals("price")){
									String price = node.getFirstChild().getNodeValue();
								}
							}
						}*/
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
