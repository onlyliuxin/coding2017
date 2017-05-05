package com.coding.basic;

public interface List {
	public void add(Object o);
	public void add(int index, Object o) throws Exception;
	public Object get(int index) throws Exception;
	public Object remove(int index) throws Exception;
	public int size();
}
