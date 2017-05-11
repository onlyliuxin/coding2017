package com.coding.basic;

public interface List<T>{
	int size();

	boolean isEmpty();

	boolean contains(Object o);

	Object[] toArray();

	boolean add(T o);

	boolean remove(T o);

	void clear();

	T get(int index);

	T set(int index, T o);

	void add(int index, T o);

	T remove(int index);

	int indexOf(T o);

	Iterator<T> iterator();

	void printf();
}
