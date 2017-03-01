package com.arrayList.basic;
/**
 * 定义了增删查方法
 * @author Jodie
 *
 */
public interface List {

	public void add(Object o);
	public void add(int index, Object o);
	public Object get(int index);
	public Object remove(int index);
	public String remove(Object o);
	public int size();
	
}
