package main.week02.litestruts;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Struts {

	public static View runAction(String actionName,
			Map<String, String> parameters) throws Exception,
			NoSuchFieldException {

		/*
		 * 
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

		XmlSaxUtil config = new XmlSaxUtil("struts.xml");
		String classname = config.getClassName(actionName);
		// 1 通过反射存入parameter
		// 没有这个类名就报错
		if (null == classname) {
			throw new CustomException("没有这个action啊！");
		}
		// 通过反射获取实例
		Class<?> controllerClass = Class.forName(classname);
		// 创建实例
		Object controller = controllerClass.newInstance();
		// 获取类中声明的全部成员变量
		Field[] fields = controllerClass.getDeclaredFields();
		// 备用
		Method m;
		// 得到该action所有result的结果合集

		// 为controller注入变量
		for (String key : parameters.keySet()) {
			for (Field f : fields) {
				if (f.getName() == key) {
					m = controllerClass.getMethod("set"
							+ key.replace(key.substring(0, 1),key.substring(0, 1).toUpperCase()), String.class);
					m.invoke(controller, parameters.get(key));
					break;
				}
			}
		}

		// 2 通过反射调用excute 获取返回值
		m = controllerClass.getMethod("execute");
		String result = (String) m.invoke(controller);

		// 3 把message放到View对象的parameters
		View view = new View();
		Map<String, String> viewParam = new HashMap<String, String>();

		// 新建并传入View的viewParam属性值
		m = controllerClass.getMethod("getMessage");
		viewParam.put("message", (String) m.invoke(controller));
		view.setParameters(viewParam);
		
		// 传入jsp路径
		view.setJsp(config.getResultView(actionName, result));

		return view;
	}

}
