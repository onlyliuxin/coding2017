package com.coderising.litestruts;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class ActionClass<E> {
	/**
	 * 类名
	 */
	private String className;
	/**
	 * 返回结果页面
	 */
	private String result;
	/**
	 * 临时存储Action下的所有result结点
	 */
	private List<Element> elements = new ArrayList<Element>();

	/**
	 * 要调用的Action本身
	 */
	private Object action;

	public Object getAction() {
		return action;
	}

	public void setAction(Object action) {
		this.action = action;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

}