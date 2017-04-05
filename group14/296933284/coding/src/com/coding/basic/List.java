package com.coding.basic;

public interface List<T> {

	boolean add(T element);

	void add(int index, T element);

	T get(int index);

	T remove(int index);

	int size();

	boolean isEmpty();

	Iterator iterator();
}
