package com.coderising.litestruts;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.coderising.litestruts.util.StringUtil;
import com.coderising.litestruts.util.XmlUtil;






public class Struts {
	
	/**
	 * struts.xml默认类路径
	 */
	public static final String STRUTS_XML_FILE = "struts.xml";

    public View runAction(String actionName, Map<String,String> parameters) {

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
    	View view = null;
    	if(StringUtil.isNotBlank(actionName)){
            ActionClass clas = getActionClass(actionName);  
            Object action = setActionValues(clas, parameters);
            clas.setAction(action);
            view = setResultValue(clas);
    	}
    
    	return view;
    }


	private View setResultValue(ActionClass clas) {
		Map<String, Object> params = new HashMap<String, Object>();
		View view = new View();
		Object obj = invokeAction(clas);
		Field[] fields = obj.getClass().getDeclaredFields();
		Method[] methods = obj.getClass().getMethods();
		// 判断某个字段是否有get方法，如果有，则将其设置在request中
		for (Field field : fields) {
			String fieldName = field.getName();
			String upperFirstLetter = fieldName.substring(0, 1).toUpperCase();
			// 获得和属性对应的getXXX()方法的名字
			String getMethodName = "get" + upperFirstLetter
					+ fieldName.substring(1);
			// 获得和属性对应的getXXX()方法
			for (Method method : methods) {
				if (StringUtil.equals(getMethodName, method.getName())) {
					field.setAccessible(true);
					try {
						params.put(field.getName(), field.get(obj));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}

		}
		view.setParameters(params);
		view.setJsp(clas.getResult());
		return view;
	}


	private Object invokeAction(ActionClass actionClass) {
		try {
			Object obj = actionClass.getAction();
			Class clas = obj.getClass();
			Method method = clas.getMethod("execute", null);
			String result = (String) method.invoke(obj, null);
			this.setInvokeResult(result, actionClass);
			actionClass.setAction(obj);
			return obj;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException("出现InvocationTargetException异常:"
					+ e.getMessage());
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new RuntimeException("出现SecurityException异常:"
					+ e.getMessage());
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new RuntimeException("出现NoSuchMethodException异常:"
					+ e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("出现IllegalAccessException异常:"
					+ e.getMessage());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException("出现IllegalArgumentException异常:"
					+ e.getMessage());
		}
	}


	private void setInvokeResult(String result, ActionClass actionClass) {
		List<Element> elements = actionClass.getElements();
		for (Element elem : elements) {
			Attribute name = XmlUtil.getAttributeByName(elem, "name");
			if (StringUtil.equals(result, name.getText())) {
				actionClass.setResult(elem.getText());
				return;
			}
		}
		throw new RuntimeException("请确定在xml配置文件中是否有名叫 [" + result
				+ "]　的返回类型结点 ");
		
	}


	@SuppressWarnings("unchecked")
	private Object setActionValues(ActionClass actionClass, Map<String, String> params) {
		try {
			// 得到Action的Class,并根据无参构造函数生成一个Action对象
			Class clas = Class.forName(actionClass.getClassName());
			Object obj = clas.newInstance();

			if (params != null && params.size() > 0) {
				Iterator<String> it = params.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					String value = params.get(key);
					String upperFirstLetter = key.substring(0, 1).toUpperCase();
					// 获得和属性对应的setXXX()方法的名字
					String setMethodName = "set" + upperFirstLetter
							+ key.substring(1);
					Method method = null;
					// 看看该页面提交的参数名中,是否在Action有set方法
					try {
						method = clas.getMethod(setMethodName,
								new Class[] { String.class });
					} catch (NoSuchMethodException e) {
						System.out.println("警告 " + actionClass.getClassName()
								+ "." + setMethodName + "("
								+ String.class.getName() + ") 不存在");
					}
					if (method != null) {
						// 如果有set方法,就调用set方法,进行赋值操作
						method.invoke(obj, new String[] { value });
					}
				}
			}
			return obj;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("出现未知异常");
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ActionClass getActionClass(String actionName) {
		// 得到struts.xml类路径
		InputStream is = this.getClass().getResourceAsStream(STRUTS_XML_FILE);
		try {
			Document doc = XmlUtil.getDocument(is);
			Element root = XmlUtil.getRoot(doc);
			// 得到所有的action结点
			List<Element> actions = XmlUtil.getElementsByName(root, "action");
			if (actions != null && actions.size() > 0) {
				for (Element elem : actions) {
					// 判断某个<result>结点元素的name属性是否与传递过来的actionName相等,如果相等那么将其method属性取出
					Attribute att = XmlUtil.getAttributeByName(elem, "name");
					if (StringUtil.equals(att.getText(), actionName)) {
						Attribute cls = XmlUtil.getAttributeByName(elem, "class");
						List<Element> results = XmlUtil.getElementsByName(elem, "result");
						ActionClass actionClass = new ActionClass();
						actionClass.setClassName(cls.getText());
						actionClass.setElements(results);
						return actionClass;
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException("struts.xml 不存在或有误");
		}
		throw new RuntimeException("找不到名称为 [" + actionName + "] 的Action映射");
	}

}