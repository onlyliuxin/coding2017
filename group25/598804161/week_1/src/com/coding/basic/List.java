package com.coding.basic;

public interface List {
	public void add(Object o);
	public void add(int index, Object o);
	Object get(int index);
	Object remove(int index);
	public int size();
}
