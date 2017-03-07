package com.qsq.study;

public interface List<E> {
	int size();
	boolean isEmpty();
	boolean add(E e);
	boolean remove(Object o);
	E remove(int index);
	E get(int index);
	E set(int index, E element);
	int indexOf(Object o);
}
