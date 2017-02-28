package com.dudy.learn01.base;
public interface MyList {
	public void add(Object o);
	public void add(int index, Object o);
	public Object get(int index);
	public Object remove(int index);
	public int size();
}