package org.coding.two;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装 Struts.xml 中 action 标签
 */
public class ActionBeanDefinition {
	public static final String TAG_ACTION = "action";
	public static final String TAG_RESULT = "result";
	public static final String TAG_ACTION_ATTR_NAME = "name";
	public static final String TAG_ACTION_ATTR_CLASS = "class";
	public static final String TAG_RESULT_ATTR_NAME = "name";
	public static final String DEFAULT_RESOURCE = "org/coding/two/struts.xml";

	private String name;
	
	private String className;
	
	private Map<String, String> resultMap;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Map<String, String> getResultMap() {
		return resultMap;
	}

	public ActionBeanDefinition(String name, String className) {
		super();
		this.name = name;
		this.className = className;
		this.resultMap = new HashMap<String, String>();
	}

	/**
	 * 
	 * @param name	result 标签的 name 属性值
	 * @param viewPath	result 标签的视图路径
	 */
	public void putResult(String name, String viewPath) {
		this.resultMap.put(name, viewPath);
//		this.resultMap.put("name", name);
//		this.resultMap.put("view", viewPath);
	}

	@Override
	public String toString() {
		return "ActionBeanDefinition [name=" + name + ", className=" + className + ", resultMap=" + resultMap + "]";
	}
	
}
