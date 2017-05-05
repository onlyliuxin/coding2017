package com.xusheng.arraylist;

public interface MyListIterator<T> extends MyIterator<T>{

	boolean hasPrevious();
	
	T previous();
	
	void add(T element);
	
	void set(T element);
}
