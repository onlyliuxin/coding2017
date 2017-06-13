package com.coderising.litestruts.bean;

import java.util.List;

/**
 * struts.xml 对应action标签
 * @author zj
 */
public class Action {

	/*名称*/
	private String name;
	
	/*类全名名称*/
	private String clazz;
	
	/*result*/
	private List<Result> results;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}
	
	
}
