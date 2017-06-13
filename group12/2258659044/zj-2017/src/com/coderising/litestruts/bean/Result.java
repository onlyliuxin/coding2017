package com.coderising.litestruts.bean;

/**
 * struts.xml 对应result标签
 * @author zj
 */
public class Result {

	/*名称*/
	private String name;
	
	/*跳转类型*/
	private String type;

	/*跳转路径*/
	private String redirectUrl;
	
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

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
	
}
