package com.coderising.litestruts;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.coderising.litestruts.Action;



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
	
public class Struts {
	//strut.xml文件所在的路径
	public static final String dir="/mycoding2017/group11/996108220/src/com/coderising/litestruts/struts.xml";
    
	/**
	 * 用户提供action动作，以及用户名和密码，对应返回view视图
	 * @param actionName 登入登出
	 * @param parameters 用户名密码
	 */
	public static View runAction(String actionName, Map<String,String> parameters) throws Exception {
		ActionConfig actionConfig=getActionConfig(actionName);
		Action action=createAction(actionConfig.getClazzName(),parameters);
		String message=getActionMessage(action);
		View view=updaView(getALL(action), actionConfig, message);
    	return view;
    }

    /**
     * 步骤0：读取配置文件，将文件中的action生成ActionDao
     * @param actionName传入action的名字
     */
    private static ActionConfig getActionConfig(String name) throws Exception {
    	 // 生成一个Dom解析器  
    	 DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();  
    	// 解析XML文件  
    	 Document document = builder.parse(dir); 
    	 NodeList actions = document.getElementsByTagName("action");
    	 int j = 0;String actionName="";String clazzName="";
    	 for (; j < actions.getLength(); j++) {
    		 	actionName= actions.item(j).getAttributes().getNamedItem("name").getNodeValue();
				if (actionName.equals(name)) {
					clazzName=actions.item(j).getAttributes().getNamedItem("class").getNodeValue();
					break;
					}
			}
    	 if (actions.item(0).getNodeType() == Node.ELEMENT_NODE) {

				Element action =(Element) actions.item(j);
				NodeList results =action.getElementsByTagName("result");
				HashMap<String, String> map=new HashMap<String, String>();
				for (int i = 0; i < results.getLength(); i++) {
					String nameString=results.item(i).getAttributes().getNamedItem("name").getNodeValue();
					String pageString=results.item(i).getTextContent();
					map.put(nameString, pageString);
				} 
				return new ActionConfig(actionName, clazzName, map);
			}
    	 return null; 	
	}
	/**
	 * 步骤1：反射创建action的对象，将name和password赋值
	 * @param clazzName
	 * @return
	 */
    private static Action createAction(String clazzName,Map<String,String> parameters) throws Exception {
		Class<?> clazz=Class.forName(clazzName);
		Object action = clazz.newInstance() ;
		Method nameSetter = action.getClass().getMethod("setName", String.class);  
		nameSetter .invoke(action, parameters.get("name")); 
		Method passwordSetter  = action.getClass().getMethod("setPassword", String.class);  
		passwordSetter.invoke(action, parameters.get("password"));
		return (Action) action;	
	}
    /**
     * 步骤2：反射运行execute方法，获得message
     * @param action
     * @return message
     */
    private static String getActionMessage (Action action) throws Exception {
    	
		return (String) action.getClass().getMethod("execute").invoke(action);
		
	}
    /**
     * 步骤3：将action中get方法与get到的值的映射关系记录到view里的Parameters表中
     * @param action
     * @return view
     */
    private static View getALL(Action action) throws Exception {
    	HashMap<String, String> map=new HashMap<>();
    	Method nameGetter = action.getClass().getMethod("getName");  
    	map.put("name", (String) nameGetter.invoke(action)); 
    	Method passwordGetter = action.getClass().getMethod("getPassword");  
    	map.put("password", (String) passwordGetter.invoke(action));
    	Method MessageGetter = action.getClass().getMethod("getMessage");  
    	map.put("message", (String) MessageGetter.invoke(action));
    	View view=new View();
    	view.setParameters(map);
		return view;
	}
    /**
     * 步骤4：将execute获得的message查找Struts配置文件将对应的页面记录到view中
     * @param view
     * @param actionConfig
     * @param message
     * @return
     */
    private static View updaView(View view,ActionConfig actionConfig,String message) {
    	return view.setJsp(actionConfig.getMessageToresult().get(message));
	}
//    public static void main(String[] args) {
//    	DocumentBuilder builder;
//		try {
//			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//			Document doc = builder.parse(dir);
//			NodeList beans = doc.getElementsByTagName("action");
//			for (int j = 0; j < beans.getLength(); j++) {
//				System.out.println(beans.item(j).getAttributes().getNamedItem("name").getNodeValue());
//				System.out.println(beans.item(j).getAttributes().getNamedItem("class").getNodeValue());
//			}
//			if (beans.item(0).getNodeType() == Node.ELEMENT_NODE) {
//
//				Element action =(Element) beans.item(0);
//				NodeList results =action.getElementsByTagName("result");
//				HashMap<String, String> map=new HashMap<String, String>();
//				System.out.println(results.getLength());
//				for (int i = 0; i < results.getLength(); i++) {
//					System.out.println(results.item(i).getAttributes().getNamedItem("name").getNodeValue());
//					System.out.println(results.item(i).getTextContent());
//				}
//			}
//			
////			NamedNodeMap name = beans.item(0).getAttributes(); 
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
    	
    	 
//	}
   
}
