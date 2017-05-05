package com.coderising.litestruts;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.coderising.litestruts.exception.StrutsException;
import com.coderising.litestruts.util.FileUtils;
import com.coding.basic.impl.ArrayList;
import com.sun.org.apache.xml.internal.security.encryption.XMLEncryptionException;



public class Struts {
	
	public static final String DETAULT_XML 		= "struts.xml";
	public static final String ACTION_TAGNAME 	= "action";
	public static final String RESULT_TAGNAME	= "result";

    public static View runAction(String actionName, Map<String,String> parameters) {

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
    	
    	return null;
    }   
    
    
    /**
     * 获取struts中配置的所有action
     * 通过dom4j实现读xml文件
     * Struts action元素的是2层结构，故，这里只解析2层
     * @param path
     * @return
     * @return:ArrayList
     */
    private static ArrayList getActionBeans(String dir,String fileName){
    	if(!FileUtils.createDir(dir)){
    		throw new IllegalArgumentException("文件路径不存在："+dir);
    	}
    	File f = new File(dir);
    	if(null == fileName || "".endsWith(fileName)){
    		fileName = DETAULT_XML;
    	}
    	SAXReader saxReader = new SAXReader();
    	try {
    		f = new File(f.getPath()+"\\"+fileName);
			Document document = saxReader.read(f);
			Element element = document.getRootElement();
			ArrayList actions = new ArrayList();
			Iterator iterator = element.elementIterator(ACTION_TAGNAME);
			// 遍历每一个action
			while(iterator.hasNext()){
				Element actionEle =  (Element) iterator.next();
				ActionBean bean = new ActionBean();
				bean.setName(actionEle.attributeValue("name"));
				bean.setClazz(actionEle.attributeValue("class"));
				Iterator it = actionEle.elementIterator(RESULT_TAGNAME);
				ArrayList results = new ArrayList();
				// 遍历每一个result
				while(it.hasNext()){
					Element resultEle = (Element) it.next(); 
					Result result = new Result();
					result.setName(resultEle.attributeValue("name"));
					result.setValue(resultEle.getText());
					results.add(result);
					System.out.println(result);
				}
				bean.setResults(results);
				System.out.println(bean);
				actions.add(bean);
			}
			System.out.println();
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new StrutsException("xml文件解析失败:,dir="+dir+",fileName="+fileName,e);
		}
    	
    	return null;
    }
    
    

    private static class ActionBean{
    	String name;
    	String clazz;
    	ArrayList results;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getClazz() {
			return clazz;
		}
		public void setClazz(String clazz) {
			this.clazz = clazz;
		}
		public ArrayList getResults() {
			return results;
		}
		public void setResults(ArrayList results) {
			this.results = results;
		}
		@Override
		public String toString() {
			return "ActionBean [name=" + name + ", clazz=" + clazz
					+ ", results=" + results + "]";
		}
		
    	
    }
    
    private static class Result{
    	String name;
    	String value;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		@Override
		public String toString() {
			return "Result [name=" + name + ", value=" + value + "]";
		}
		
    	
    }
    
    public static void main(String[] args) {
		Struts.getActionBeans("src/com/coderising/litestruts/","");
	}
}
