package main.week02.litestruts;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Struts {

	public static String[] str = new String[10];

	public static View runAction(String actionName,
			Map<String, String> parameters) throws Exception {

		/*
		 * 
		 * 0. 读取配置文件struts.xml
		 * 
		 * 1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象）
		 * 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 ("name"="test" ,
		 * "password"="1234") , 那就应该调用 setName和setPassword方法
		 * 
		 * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		 * 
		 * 3. 通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用， 把值和属性形成一个HashMap , 例如
		 * {"message": "登录成功"} , 放到View对象的parameters
		 * 
		 * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp，
		 * 放到View对象的jsp字段中。
		 */

		// 读取xml文档的元素值
		// 1.获取工厂
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 2.产生解析器
		DocumentBuilder builder = factory.newDocumentBuilder();
		// 3.解析xml文档，得到代表文档的document对象
		Document document = builder.parse(new File(
				"./src/main/week02/litestruts/struts.xml"));
		// 根据标签名获取节点列表
		NodeList list = document.getElementsByTagName("action");

		HashMap<String, String> actionMap = new HashMap<String, String>(); 

		for (int i = 0; i < list.getLength(); i++) {
			// 获取节点列表里的节点
			Element action = (Element) list.item(i);
			// 获取节点的属性值
			actionMap.put(action.getAttribute("name"), action.getAttribute("class"));
		}
		
		

		return null;
	}

}
