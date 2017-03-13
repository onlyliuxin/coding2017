package cn.xl.c2;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 0. 读取配置文件struts.xml
 * 1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象）据parameters中的数据，调用对象的setter方法， 
 *    例如parameters中的数据是 ("name"="test" ,  "password"="1234") , 那就应该调用 setName和setPassword方法
 * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
 * 3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
 *    通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  放到View对象的parameters
 * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
 * 	     放到View对象的jsp字段中。
 * 
 */

public class Struts {


	private static  String filePath = "src/main/java/cn/xl/c2/struts.xml";

	private static  long time; 

	private static  List<Object> reList = new ArrayList<Object>(); 


	@SuppressWarnings("unchecked")
	public static View runAction(String actionName, Map<String,String> parameters) {

		View    view = null;
		Class<?> cls = null;
		Object   Obj = null;
		Map<String,Object> action = null;
		List<Object>   resultList = null;
		Iterator<Object> restIter = null;
		List<Object> configlist = getConfigInfo();
		Iterator<Object> lter = configlist.iterator();
		while(lter.hasNext()){
			action = (Map<String,Object>)lter.next();
			if(actionName.equals(action.get("actionName"))){
				System.out.println("action.get(actionClass)"+action.get("actionClass"));
				try {
					//得到对象
					cls = Class.forName(action.get("actionClass").toString());
					Obj = cls.newInstance();
					//获取到方法对象
					Method setName = cls.getDeclaredMethod("setName", String.class);
					Method setPassword = cls.getDeclaredMethod("setPassword", String.class);
					Method exectue = cls.getDeclaredMethod("execute");
					//执行方法
					setName.invoke(Obj,parameters.get("name").toString());
					setPassword.invoke(Obj,parameters.get("password").toString());
					String reMessage = (String)exectue.invoke(Obj);
					//匹配结果，封装view
					resultList =  (List<Object>) action.get("result");
					restIter = resultList.iterator();
					while(restIter.hasNext()){
						Map<String,Object> result = (Map<String,Object>) restIter.next();
						if(reMessage.equals(result.get("resultName"))){
							view = new View();
							view.setJsp(result.get("resultMapper").toString());
							view.setParameters(parameters);
						}
					}

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("e=="+e);
				}  

			}
		}

		return view;
	}    

	/** 
	 * 如果内存中没有，则先去解析，如果存在并且配置文件未修改，则直接从内存中获取  
	 * @return configInfo
	 */  
	public static List<Object> getConfigInfo(){  
		if(reList.size()==0 || getTime(filePath)!=time){  
			return readConfigXML();  
		}else {  
			return  reList;
		}  
	}  

	/**
	 * 读取配置文件信息，并返回配置文件内容
	 * 
	 */
	private static List<Object> readConfigXML() {
		DocumentBuilderFactory   dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();  
			Document doc = db.parse(filePath);  
			time = getTime(filePath);
			NodeList actionList = doc.getElementsByTagName("action"); 
			Map<String,Object>  actionMap ;
			
			for (int i = 0; i < actionList.getLength(); i++)  
			{  
				actionMap =  new HashMap<String,Object>();
				Node action = actionList.item(i);  
				Element elem = (Element) action; 
				String actionName = elem.getAttributes().getNamedItem("name").getNodeValue();
				String actionClass = elem.getAttributes().getNamedItem("class").getNodeValue();
				actionMap.put("actionName",actionName);
				actionMap.put("actionClass",actionClass);
				// 获得根元素下的子节点
				List<Object> resultList = new ArrayList<Object>();
				for (Node node = action.getFirstChild(); node != null; node = node.getNextSibling())  
				{  
					if (node.getNodeType() == Node.ELEMENT_NODE)  
					{  
						Map<String,Object> resultMap = new HashMap<String,Object>();
						String resultName = node.getAttributes().getNamedItem("name").getNodeValue();
						String resultMapper = node.getFirstChild().getNodeValue();
						resultMap.put("resultName", resultName);
						resultMap.put("resultMapper", resultMapper);
						resultList.add(resultMap);
					}  

				}
				actionMap.put("result",resultList);
				reList.add(actionMap);
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("e=="+e.getMessage());
		}
		return reList;  	
	}

	/** 
	 * 获取到文件最后修改时间 
	 * @return 
	 */  
	public static long getTime(String path){  
		File f = new File(path);    
		long  lastTime=f.lastModified();  
		System.out.println("lastTime:::::"+lastTime);
		return lastTime;  
	}  


	public static void main(String[] args){


	}


}
