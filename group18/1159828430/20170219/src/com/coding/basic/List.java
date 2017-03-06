package com.coding.basic;
/**
 * @author Hipple
 * @Time：2017年2月20日 下午8:52:08
 * @version 1.0
 */
public interface List {
	public boolean add(Object o);
	public void add(int index, Object o);
	public Object get(int index);
	public Object remove(int index);
	public int size();
}