package com.util;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class Dom4JforXML {
	@Test  
    public void test() throws Exception{  
        //创建SAXReader对象  
        SAXReader reader = new SAXReader();  
        //Dom4JforXML.class.getResourceAsStream("");
        //读取文件 转换成Document  
        //System.out.println(this.getClass().getResource("/").getPath()+"struts.xml");
        Document document = reader.read(Dom4JforXML.class.getResource("struts.xml"));  
        //获取根节点元素对象  
        Element root = document.getRootElement();  
        String xpath1 = "//action[@name='login']";  
    	
    	List list1=root.selectNodes(xpath1);
    	Iterator it = list1.iterator();  
        while (it.hasNext()) {  
            Element elt = (Element) it.next();  
            Attribute attr = elt.attribute("name");  
            
        }  
        //遍历  
        //listNodes(root);  
    }  
	@Test
	public void test2(){
		//Dom4JforXML.class.getClass().getResourceAsStream("struts.xml");
		String name="getName";
		//name
		String name1=name.substring(3);
		String result=name1.substring(0, 1).toLowerCase()+name1.substring(1);
		System.out.println(result);
		
	}
      
    //遍历当前节点下的所有节点  
    public void listNodes(Element node){  
        System.out.println("当前节点的名称：" + node.getName());  
        //首先获取当前节点的所有属性节点  
        List<Attribute> list = node.attributes();  
        //遍历属性节点  
        for(Attribute attribute : list){  
            System.out.println("属性"+attribute.getName() +":" + attribute.getValue());  
        }  
        //如果当前节点内容不为空，则输出  
        if(!(node.getTextTrim().equals(""))){  
             System.out.println( node.getName() + "：" + node.getText());    
        }  
        //同时迭代当前节点下面的所有子节点  
        //使用递归  
        Iterator<Element> iterator = node.elementIterator();  
        while(iterator.hasNext()){  
            Element e = iterator.next();  
            listNodes(e);  
        }  
    }  
}
