package cn.test;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.task2.Struts;

public class TestUtil {
	
	public static void main(String[] args) {
		SAXReader read = new SAXReader();
    	Document document = null;
    	try {
			document = read.read(Struts.class.getClassLoader().getResourceAsStream("struts.xml"));
			//获取唯一的根节点
			Element root = document.getRootElement();
			//获取子节点的集合
			List<Element> childNode = root.elements();
			
			for(Element ele : childNode){
				System.out.println(ele.attributeValue("name")+"  "+ele.attributeValue("class"));
				List<Element> list2 = ele.elements();
				for(Element e:list2){
					System.out.println(e.getName()+"--"+e.attributeValue("name")+"  "+e.getText());
				}
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
	}
}
