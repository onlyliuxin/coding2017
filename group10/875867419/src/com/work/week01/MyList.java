package com.work.week01;

public interface MyList<E>{
	boolean add(E element);
	void add(int index, E element);
	E get(int index);
	E remove(int index);
	int size();
	boolean isEmpty();
	MyIterator<E> iterator();
}
