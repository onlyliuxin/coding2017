/**
 * 
 */
package com.coderising.litestruts;

import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * Title:
 * Description:
 * @author HuangLiang
 * @2017年3月17日上午10:55:39
 * 
 */
public class Test {

	/**Description:
	 * @param args
	 * @author HuangLiang 2017年3月17日 上午10:55:39
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			String name1 = "getName";
			System.out.println(name1.substring(3, 4).toLowerCase()+name1.substring(4));
			try {
				Class<?> class1 = Class.forName("com.coderising.litestruts.LoginAction");
				Method[] methos1 = class1.getMethods();
				for(int i=0;i<methos1.length;i++){
					Class<?> para[] = methos1[i].getParameterTypes();
					throw new ClassNotFoundException();
				}
				System.out.println("输出");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	/**
	 * 获取XML子标签
	 * 
	 * @param element
	 *            标签节点
	 * @param sonMark
	 *            子标签
	 * @return
	 */
	public static Element getChild(Element element, String sonMark) {
		return element == null ? null : element.getChild(sonMark);
	}

	/**
	 * 获取XML子标签们
	 * 
	 * @param element
	 *            标签节点
	 * @param sonMark
	 *            子标签
	 * @return
	 */
	public static List getChildren(Element element, String sonMark) {
		return element == null ? null : element.getChildren(sonMark);
	}

	/**
	 * s 获取XML标签值
	 * 
	 * @param element
	 * @return
	 */
	public static String getValue(Element element) {
		return element == null ? "" : element.getValue();
	}
	/**
	 * s 获取XML属性值
	 * 
	 * @param element
	 * @return
	 */
	public static String getAttributeValue(Element element, String attribute) {
		return element == null ? null : element.getAttributeValue(attribute);
	}

}
