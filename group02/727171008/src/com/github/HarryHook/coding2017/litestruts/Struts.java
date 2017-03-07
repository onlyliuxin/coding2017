package com.github.HarryHook.coding2017.litestruts;

import java.io.File;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Struts 
{

	public static View runAction(String actionName, Map<String, String> parameters) 
	{
		return null;
	}
	public static void main(String[] args) throws Exception
    {
    	/*
    	Class clz =	Class.forName("com.github.HarryHook.coding2017.litestruts.LoginAction");
		//创建类的实例
		Object obj = clz.newInstance();
		//得到方法的引用
		Method method = clz.getDeclaredMethod("setName", java.lang.String.class);
		//调用该方法
		method.invoke(obj, "test");
		LoginAction b = (LoginAction) obj;
		System.out.println(b.getName());
		
		method = clz.getDeclaredMethod("setPassword", java.lang.String.class);
		method.invoke(obj, "1234");
		b = (LoginAction) obj;
		System.out.println(b.getPassword());
		*/
    	
    	 //获得dom解析器工厂，用于创建具体的解析器
    	 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    	 //获得具体的dom解析器
    	 DocumentBuilder db = dbf.newDocumentBuilder();
    	 //解析一个xml文档,获得Document对象(根节点)
    	 Document doc = db.parse(new File("src/com/github/HarryHook/coding2017/litestruts/struts.xml"));
    	 //对文档进行解析
    	 Element root = doc.getDocumentElement(); 
    	 parseElement(root);
    	 System.out.println("");
    	      	  	  	
    }
    private static void parseElement(Element element)
    {
    	String tagName = element.getNodeName();
    	NodeList  children = element.getChildNodes();
    	System.out.print("<" + tagName);
    	
    	//element元素的所有属性所构成的NamedNodeMap队形，要对其进行判断
    	NamedNodeMap nnm = element.getAttributes();
    	
    	//如果元素存在属性
    	if(nnm != null)
		{
			for(int i=0; i<nnm.getLength(); i++)
			{
				Attr attr = (Attr)nnm.item(i);
				String attrName = attr.getName();
				String attrValue = attr.getValue();
				System.out.print(" " + attrName + " = " + "\"" + attrValue + "\"");
			}
		}
    	System.out.print(">");
    	
    	for(int i=0; i<children.getLength(); i++)
    	{
    		Node node = children.item(i);
    		//获得节点的类型
    		short nodeType = node.getNodeType();
    		if(nodeType == Node.ELEMENT_NODE)
    		{
    			//是元素。继续递归
    			parseElement((Element)node);
    		}
    		else if(nodeType == Node.TEXT_NODE)
    		{
    			//递归出口
    			System.out.print(node.getNodeValue());
    		}
    		else if(nodeType == Node.COMMENT_NODE)
    		{
    			//获得注释内容
    			System.out.print("<!----");
    			Comment comment = (Comment)node;
    			String data = comment.getData();
    			System.out.print(data);
    			System.out.print("---->");
    		}
    	}
    	System.out.print("</" + tagName + ">");
    	
    }
 

	
}