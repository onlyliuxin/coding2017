package com.coding.basic;

/**
 * 简单list接口
 *
 */
public interface SimpleList {
	public void add(Object o);
	public void add(int index,Object o );
	public Object get(int index);
	public Object remove(int index);
	public int size();

}
