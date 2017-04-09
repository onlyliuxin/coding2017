package com.ecust.test;

public interface GList<T> { 
	int size();
	boolean isEmpty();
	boolean contains(Object o);
	Object[] toArray();
	boolean add(T t);
	boolean remove(T t);
	void clear();
	T get (int index);
	T set (int index,T t);
	void add(int index,T t);
	T remove(int index);
	int indexof(T t);
	GIterator<T> iterator();

}
