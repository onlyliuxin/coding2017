package week2_miniStruts;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {
	@SuppressWarnings("unchecked")
	public static View runAction(String actionName,Map<String,String>parameters){
		if(actionName == null || parameters == null) return null;
		List<Element> actions = null; 
		try {
			File xmlfile = new File(System.getProperty("user.dir")+"\\bin\\week2\\struts.xml");
			Document doc = new SAXReader().read(xmlfile);
			Element root = doc.getRootElement();
			actions = root.elements();
		} catch (DocumentException e) {
		    e.printStackTrace();
		}
		
		String className="";
		Element curActNode = null;
		for(int i=0;i<actions.size() && curActNode == null;i++){// get curNode and className from xml files
			List<Attribute> attrs = actions.get(i).attributes();
			for(int j=0;j<attrs.size();j++){
				String attrName = attrs.get(j).getName();
				if(attrName.equals("name") && attrs.get(j).getValue().equals(actionName))
					curActNode = actions.get(i);
				if(attrName.equals("class")) 
					className = attrs.get(j).getValue();
			}
		}
		if(curActNode == null) return null;   // TODO
		Object class1Instance = null;
		Class<?> class1 = null;
		try {
			class1 = Class.forName(className);
			class1Instance = class1.newInstance();
		} catch (Exception  e) {
			e.printStackTrace();
		}
		for(String key : parameters.keySet()){
			String methodName = "set"+(new StringBuilder()).append(Character.toUpperCase(key.charAt(0))).append(key.substring(1)).toString();
			Object methodPara=parameters.get(key);
			try {
				Method method =class1.getMethod(methodName, String.class);
				method.invoke(class1Instance, methodPara);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		Object exeResult = null;
		try {
			Method method =class1.getMethod("execute");
			exeResult = method.invoke(class1Instance);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String jsp = null;
		List<Element> results = curActNode.elements();
		for(int i=0;i<results.size() && jsp==null;i++){
			List<Attribute> attrs = results.get(i).attributes();
			for(int j=0;j<attrs.size() && jsp==null;j++){
				Attribute attr = attrs.get(j);
				if(attr.getName().equals("name") && attr.getValue().equals(exeResult.toString())) 
					jsp = results.get(i).getText();
			}
		}
		
		View view = new View();
		view.setJsp(jsp);
		Map<String,String> para = new HashMap<String,String>();
		view.setParameters(para);
		
		Field [] fields = class1.getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			String propName = fields[i].getName();
			String getMethodName = "get"+(new StringBuilder()).append(Character.toUpperCase(propName.charAt(0))).append(propName.substring(1));
			Object getReturn = null;
			try {
				Method method = class1.getMethod(getMethodName);
				getReturn = method.invoke(class1Instance);
			} catch (Exception e) {
				// e.printStackTrace(); // 有些属性没有定义get方法，这不是异常
			}
			if(getReturn != null)
				para.put(propName, getReturn.toString());
		}
		
		return view;
	}
}
