package com.zzk.coding2017.zuoye_2.litestruts;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Struts {

	public static View runAction(String actionName,
			Map<String, String> parameters) {

		View v = new View();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db
					.parse("src/com/zzk/coding2017/zuoye_2/litestruts/struts.xml");
			Element element = document.getDocumentElement();
			NodeList actionList = element.getElementsByTagName("action");// 取得所有的action节点
			for (int i = 0; i < actionList.getLength(); i++) {// 遍历所有的actin节点，寻找是否存在名为actionName的节点
				Element e = (Element) actionList.item(i);
				String name = e.getAttribute("name");
				if (name.equals(actionName)) {// 找到对应的类，给类的属性赋值并调用execute函数执行
					try {
						String cname = e.getAttribute("class");
						Class C = Class.forName(cname);
						Object o = C.newInstance();
						Method m1 = C.getDeclaredMethod("execute");

						// 取得所有的getter和setter方法
						Method[] methods = C.getMethods();
						Hashtable<String, Method> getMethods = new Hashtable<>();
						Hashtable<String, Method> setMethods = new Hashtable<>();
						// 定义正则表达式，从方法中过滤出getter / setter 函数.
						String gs = "get(\\w+)";
						Pattern getM = Pattern.compile(gs);
						String ss = "set(\\w+)";
						Pattern setM = Pattern.compile(ss);
						// 把方法中的"set" 或者 "get" 去掉
						String rapl = "$1";
						String param;
						for (int k = 0; k < methods.length; ++k) {
							Method m = methods[k];
							String methodName = m.getName();
							if (Pattern.matches(gs, methodName)) {
								param = getM.matcher(methodName)
										.replaceAll(rapl).toLowerCase();
								if (!param.equals("class")) {
									getMethods.put(param, m);
								}
							} else if (Pattern.matches(ss, methodName)) {
								param = setM.matcher(methodName)
										.replaceAll(rapl).toLowerCase();
								setMethods.put(param, m);
							}
						}

						// 给action所对应类的参数赋值
						Iterator<Map.Entry<String, String>> it = parameters
								.entrySet().iterator();
						while (it.hasNext()) {
							Entry<String, String> en = it.next();
							String Mname = en.getKey();
							Method gMethod = setMethods.get(Mname);
							if (gMethod != null) {
								gMethod.invoke(o, en.getValue());
							}
						}

						// 调用actin对应类的执行方法
						String result = (String) m1.invoke(o, null);

						// 遍历action子节点，查找对应结果,并返回参数
						NodeList childNodes = e.getChildNodes();
						/*
						 * NodeList childNodes = element
						 * .getElementsByTagName("result");
						 */
						for (int j = 0; j < childNodes.getLength(); j++) {
							if (childNodes.item(j) instanceof Element) {
								Element Child = (Element) childNodes.item(j);//这里不加判断会报类型无法转换的错误，查找原因，是说xml中的某些文本会被当做节点存入nodelist中
								String ChileName = Child.getAttribute("name");
								if (ChileName.equals(result)) {
									v.setJsp(Child.getTextContent());//不能用getnodevalue，而要用gettextcontent

									// 给view的parameters参数赋值
									Map<String, String> vparameters = new HashMap<>();
									for (Entry<String, Method> entry : getMethods
											.entrySet()) {
										String tmp = (String) entry.getValue()
												.invoke(o);
										vparameters.put(entry.getKey(), tmp);
									}
									v.setParameters(vparameters);
									return v;
								}

							}
						}
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

		return null;
	}

}
