package com.xusheng.arraylist;

public interface MyCollection<T> extends MyIterator<T>{

	int size();
	
	boolean isEmpty();
	
	void clear();
	
	boolean contains(T element);
	
	boolean add(T element);
	
	boolean remove(T element);
	
	MyIterator<T> myIterator();
	
}
