package com.coderising.litestruts;

import org.apache.commons.lang3.StringUtils;

/** 
 * @author greenhills
 * @version 创建时间：2017年2月27日 下午10:39:20 
 * 
 */
public class ResultMapping {
	/**
	 * 映射结构：<result name="success" type="redirect">/jsp/homepage.jsp</result>
	 */
	private String name;
	private String type;
	private String urlPath;
	
	public ResultMapping() {
		super();
	}
	
	public ResultMapping(String name, String type, String urlPath) {
		super();
		this.name = StringUtils.isBlank(name) ? "success" : name; //默认成功
		this.type = StringUtils.isBlank(type) ? "dispatcher" : type; //默认转发
		this.urlPath = urlPath;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrlPath() {
		return urlPath;
	}
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
}
