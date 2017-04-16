package com.coding.basic;

public interface List<T> {
	public void add(T t);
	public void add(int index, T t);
	public Object get(int index);
	public Object remove(int index);
	public int size();
	public Iterator iterator();
}
