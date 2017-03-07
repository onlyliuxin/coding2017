package com.coding.basic;

public interface List<T> {
	public boolean add(T o);
	public boolean add(int index, T o);
	public T get(int index);
	public T remove(int index);
	public int size();
}
