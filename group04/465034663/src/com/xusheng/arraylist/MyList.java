package com.xusheng.arraylist;

public interface MyList<T> extends MyCollection<T> {

	T get(int index);
	
	T set(int index,T element);
	
	void add(int index,T element);
	
	void remove(int index,T element);
	
	MyListIterator<T> myListIterator();
}
