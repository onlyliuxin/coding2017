package day20170226.homework.struts;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import common.BeanHelper;

/**
 * 0. 读取配置文件struts.xml
 *	
 *	1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
 *	        据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
 *	    ("name"="test" ,  "password"="1234") ,     	
 *	        那就应该调用 setName和setPassword方法
 *
 * 	2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
 *
 *	3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
 *	        通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
 *	        放到View对象的parameters
 *
 *	4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
 *     放到View对象的jsp字段中。
*/
public class Struts {
	
	public static View runAction(String actionName, Map<String,String> parameters) {
		View view = new View();
		Map<String, Object> viewP = new HashMap<String,Object>();
		InputStream is = Struts.class.getResourceAsStream("/day20170226/homework/struts/struts.xml");
		SAXReader reader = new SAXReader(); 
		Document doc;
		
		try {
			doc = reader.read(is);
			Element root = doc.getRootElement(); 
			Element actionNode = getNode("action", "name", actionName, root); 
			if (actionNode == null) {
				throw new RuntimeException("do not have this action: " + actionName);
			}
			Class<?> c = Class.forName(actionNode.attributeValue("class"));
			Object ob = BeanHelper.invokeClassFromMap(c, parameters);
			String resultName = String.valueOf(BeanHelper.excuteBeanMethod(ob, "execute", null));
			Element resultNode = getNode("result", "name", resultName, actionNode); 
			view.setJsp(resultNode.getTextTrim());
			viewP = BeanHelper.getBeanFields(ob);
			view.setParameters(viewP);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
		
    	return view;
    }    
	
	/**
	 * 从父节点中找到eleName的节点并属性值 = value 的节点
	 * @param eleName   节点名称
	 * @param attrName  节点属性名称
	 * @param value		属性值
	 * @param parent	父节点
	 * @return
	 */
	public static Element getNode(String eleName, String attrName, String value, Element parent){
		List<Element> actions = parent.elements(eleName);
		for(Element activeNode : actions){
			if(activeNode.attributeValue(attrName).equals(value)){
				return activeNode;
			}
		}
		return null;
	}
}
