package com.coderising.litestruts;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/** 
 * @author greenhills
 * @version 创建时间：2017年2月27日 下午10:36:35 
 * 
 */
public class ActionMapping {
	/**
	 * action名称
	 */
	private String name;
	/**
	 * 执行action的类
	 */
	private String className;
	/**
	 * 执行action的类方法
	 */
	private String method;
	/**
	 * 保存ResultMapping结果，以result标签的name作为key
	 */
	private Map<String,ResultMapping> resultMappings=new HashMap<String,ResultMapping>();
	
	public ActionMapping() {}

	public ActionMapping(String name, String className, String method) {
		this.name = name;
		this.className = className;
		this.method = StringUtils.isBlank(method) ? "execute" : method; //未配置时，默认查找execute方法执行
	}
	
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
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Map<String, ResultMapping> getResultMappings() {
		return resultMappings;
	}
	public void setResultMappings(Map<String, ResultMapping> resultMappings) {
		this.resultMappings = resultMappings;
	}
	
	//扩展的方法，用于保存
	public void setResultMappings(String key, ResultMapping value) {
		this.resultMappings.put(key, value);
	}
}
