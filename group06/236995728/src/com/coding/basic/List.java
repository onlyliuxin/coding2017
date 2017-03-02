package com.coding.basic;

/**
 * 
 * @author 236995728
 *
 */
public interface List {
	public void add(Object o);
	public void add(int index, Object o);
	public Object get(int index);
	public Object remove(int index);
	public int size();
}
