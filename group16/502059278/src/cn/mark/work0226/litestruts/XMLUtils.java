package cn.mark.work0226.litestruts;

import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLUtils {
	/**
	 * 获取Document
	 * @param filePath 配置文件路径名
	 * @return Document对象
	 */
	public static Document getDocument(String filePath){
		//1.创建解析器
		SAXReader reader = new SAXReader();
		//2.解析XML文档，返回document对象
		Document dom = null;
		try {
			dom = reader.read(filePath);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return dom;
	}
	/**
	 * 获取指定action元素
	 * @param doc Document
	 * @param actionName 要获取的元素属性名
	 * @return 包含所要属性的元素
	 */
	public static Element getElement(Document doc , String actionName){
		Element result = null;
		Element root = doc.getRootElement();
    	List<Element> elements = root.elements();
    	for(Element e : elements){
    		Attribute attr = e.attribute("name");
    		if(attr.getValue().equals(actionName)){
    			result = e;
    			return result;
    		}
    	}
		return result;
	}
	
}
