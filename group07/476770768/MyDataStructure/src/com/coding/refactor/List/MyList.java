package com.coding.refactor.List;

/**
 * 
 * created by nelson on 2017/03/03
 *
 */
public interface MyList<T> {
	
	int size();
	
	boolean isEmpty();
	
	void add(T o);
	
	void add(int index, T o);
	
	boolean contains(Object o);
	
	Object[] toArray();
	
	boolean remove(T o);
	
	T remove(int index);
	
	T get(int index);
	
	T set(int index, T element);
	
	void clear();
	
	int indexOf(T o);
	
	MyIterator<T> iterator();
}
