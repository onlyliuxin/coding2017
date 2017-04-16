package com.coding.mybasic;

public interface List {
	public void add(Object element);
	public void add(int index, Object element);
	public Object get(int index);
	public Object remove(int index);
	public int size();
	public Iterator iterator();
}
