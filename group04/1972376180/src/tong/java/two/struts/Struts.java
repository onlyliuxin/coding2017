package tong.java.two.struts;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Struts {
	private static Root strutsRoot;
	private static Action action = null;

	public static View runAction(String actionName,
			Map<String, String> parameters) {
		View view = new View();

		/*
		 * 0. 读取配置文件struts.xml
		 * 
		 * 1. 根据actionName找到相对应的class ， 例如LoginAction, 通过反射实例化（创建对象）
		 * 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 ("name"="test" ,
		 * "password"="1234") , 那就应该调用 setName和setPassword方法
		 * 
		 * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		 * 
		 * 3. 通过反射找到对象的所有getter方法（例如 getMessage）, 通过反射来调用， 把值和属性形成一个HashMap , 例如
		 * {"message": "登录成功"} , 放到View对象的parameters
		 * 
		 * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值， 确定哪一个jsp，
		 * 放到View对象的jsp字段中。
		 */
		
		//读取配置文件
		strutsRoot = readStruts();
		for (Action a : strutsRoot.getAction()) {
			if (actionName.contains(a.getName())) {
				action = a;
			}
		}
		if (action != null) {
			try {
				if (action.getName().equals("login")) {
					//通过反射生成LoginAction实例
					LoginAction loginAction = (LoginAction) Class.forName(
							action.getClassName()).newInstance();
					loginAction.setName(parameters.get("name"));
					loginAction.setPassword(parameters.get("password"));
					//通过反射，执行execute方法
					Method method = LoginAction.class.getMethod("execute", null);//
					String result = (String) method.invoke(loginAction, null);
					HashMap<String, String> params = new HashMap();
					params.put("message", loginAction.getMessage());
					view.setParameters(params);
					for (Result r : action.getResults()) {
						if (result.equals(r.getName())) {
							view.setJsp(r.getPage());
						}
					}

				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

		}
		return view;
	}

	private static Root readStruts() {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(new File(
					"/tong/java/two/struts/struts.xml"));
			Root strutsRoot = new Root();
			Element root = document.getRootElement();
			ArrayList<Action> actionList = new ArrayList<Action>();
			List<Element> elements = root.elements("action");
			for (Element e : elements) {
				Action action = new Action();
				action.setName(e.attributeValue("name"));
				action.setClassName(e.attributeValue("class"));
				List<Element> results = e.elements("result");
				ArrayList<Result> resultList = new ArrayList<Result>();
				for (Element e_result : results) {
					Result result = new Result();
					result.setName(e_result.attributeValue("name"));
					result.setPage(e_result.getTextTrim());
					resultList.add(result);
				}
				action.setResults(resultList);
				actionList.add(action);
			}
			strutsRoot.setAction(actionList);
			return strutsRoot;
		} catch (DocumentException e) {
			throw new RuntimeException();
		}
	}

}
