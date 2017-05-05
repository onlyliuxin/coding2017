package com.coding.basic;
/** 
 * 集合接口
 * @author greenhills
 * @version 创建时间：2017年2月19日 下午10:49:40 
 * 
 */
public interface List {
	public void add(Object o);
	public void add(int index, Object o);
	public Object get(int index);
	public Object remove(int index);
	public int size();
	public int capacity();
	boolean isEmpty();
}
