package com.bruce.homework0305.mystruts;

import com.sun.deploy.util.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) throws DocumentException {

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
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("src/com/bruce/homework0305/mystruts/struts.xml"));
        if(document == null){
            throw new DocumentException("找不到对应的xml文件");
        }
        Element root = document.getRootElement();
        //TODO 拿到根结点后，如果操作，是否需要遍历文件获得每个节点元素

        return null;
    }

    private void listNodes(Element e){
        Map<String,String> nodesMap = new HashMap<>();
        //获取当前节点的所有属性节点
        List<Attribute> attributes = e.attributes();
        //遍历属性节点
        for(Attribute attribute : attributes){
//            nodesMap.put(attribute.getName(),attribute.getValue());
            System.out.println(attribute.getName()+" : "+attribute.getValue());
        }
        //如果当前节点内容不为空，输出节点内容
        if(!"".equals(e.getTextTrim())){
            System.out.println(e.getName()+" = "+e.getText());
        }
        //迭代当前节点下的所有子节点,递归调用
        Iterator<Element> elementIterator = e.elementIterator();
        while(elementIterator.hasNext()){
            Element next = elementIterator.next();
            listNodes(next);
        }
    }

}
