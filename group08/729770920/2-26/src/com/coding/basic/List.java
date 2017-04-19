package com.coding.basic;

public interface List<E> {
	void add(E e);

	void add(int index, E e);

    void clear();

	E get(int index);

	E remove(int index);

	int size();

    boolean contains(E e);

    Iterator<E> iterator();
}
