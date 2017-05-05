package com.xusheng.arraylist;

public interface MyIterator<T> {

	boolean hasNext();
	
	T next();
	
	void remove();
}
