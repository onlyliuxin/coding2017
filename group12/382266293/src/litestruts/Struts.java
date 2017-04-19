package litestruts;

import util.ActionXMLreader;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.dom4j.Node;

public class Struts {

	private static Object actionObj = null;
	private static String address = "src/litestruts/struts.xml";
	private static ActionXMLreader reader = new ActionXMLreader();

	@SuppressWarnings("unchecked")
	public static View runAction(String actionName, Map<String, String> parameters) {

		Node root = reader.getRootNode(address);
		String clazz = reader.parseClass(root, actionName);
		actionObj = getObj(clazz);
		BeanInfo bi = getBeanInfo(actionObj);
		PropertyDescriptor[] pd = bi.getPropertyDescriptors();

		setParameters(actionObj, pd, parameters);
		String executeResult = getResult(actionObj, bi, "execute");
		String jsp = reader.parseResult(root, actionName, executeResult);
		Map<String, String> readParamters = getReadParameters(actionObj, pd);

		View view = new View();
		view.setJsp(jsp);
		view.setParameters(readParamters);

		return view;
	}

	private static Object getObj(String clazz) {
		@SuppressWarnings("rawtypes")
		Class cls = null;

		try {
			cls = Class.forName(clazz);
			return cls.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static BeanInfo getBeanInfo(Object obj) {

		BeanInfo bi = null;
		try {
			bi = Introspector.getBeanInfo(obj.getClass(), Object.class);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return bi;
	}

	private static void setParameters(Object obj, PropertyDescriptor[] pd, Map parameters) {

		for (int i = 0; i < pd.length; i++) {
			String name = pd[i].getName();
			if (parameters.containsKey(name))
				try {
					pd[i].getWriteMethod().invoke(obj, parameters.get(name));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
		}
	}

	private static String getResult(Object obj, BeanInfo bi, String execute) {
		MethodDescriptor[] methods = bi.getMethodDescriptors();
		for (int i = 0; i < methods.length; i++) {
			String methodName = methods[i].getName();
			if (methodName.equals(execute))
				try {
					return (String) methods[i].getMethod().invoke(actionObj);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static Map getReadParameters(Object obj, PropertyDescriptor[] pd) {

		Map<String, String> viewParams = new HashMap<String, String>();

		for (int i = 0; i < pd.length; i++) {
			String readMethod = pd[i].getReadMethod().getName().substring(3);
			String value = null;
			try {
				value = (String) pd[i].getReadMethod().invoke(obj);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			viewParams.put(readMethod.toLowerCase(), value);
		}
		return viewParams;
	}

}