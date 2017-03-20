package com.coding.basic;

/**
 * Created by songbao.yang on 2017/2/21.
 *
 */
public interface List {
	public void add(Object o);
	public void add(int index, Object o);
	public Object get(int index);
	public Object remove(int index);
	public int size();
}
