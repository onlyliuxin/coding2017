package com.coding.basic.list;

public interface List {
	public void add(Object o);

	public void add(int index, Object o) throws ArrayIndexOutOfBoundsException;

	public Object get(int index);

	public Object remove(int index);

	public int size();
	
	public Iterator iterator();
}
